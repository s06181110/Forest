package forest;

import java.util.ArrayList;
import java.util.Collections;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 *  樹状整列におけるフォレスト（木・林・森・亜格子状の森）を担うクラスになります。
 * 
 */
public class Forest extends Object {

	/**
	 *  ノード（節）群（たち）を記憶するフィールドです。
	 * 
	 */
	private ArrayList<Node> nodes;

	/**
	 *  ブランチ（枝）群（たち）を記憶するフィールドです。
	 * 
	 */
	private ArrayList<Branch> branches;

	/**
	 *  樹状整列したフォレスト（森）の領域（矩形）を記憶するフィールドです。
	 * 
	 */
	private Rectangle bounds;

	/**
	 *  このクラスのインスタンスを生成するコンストラクタです。
	 * 
	 */
	public Forest() {
		this.nodes = new ArrayList<Node>();
		this.branches = new ArrayList<Branch>();
		this.bounds = null;
		return;
	}

	/**
	 *  ブランチ（枝）を追加するメソッドです。
	 *  @param aBranch ブランチ（枝）
	 * 
	 */
	public void addBranch(Branch aBranch) {
		this.branches.add(aBranch);
		this.flushBounds();
		return;
	}

	/**
	 *  ノード（節）を追加するメソッドです。
	 *  @param aNode ノード（節）
	 * 
	 */
	public void addNode(Node aNode) {
		this.nodes.add(aNode);
		this.flushBounds();
		return;
	}

	/**
	 *  樹状整列するトップ（一番上位）のメソッドです。
	 * 
	 */
	public void arrange() {
		this.arrange(null);
		return;
	}

	/**
	 *  樹状整列するセカンドレベル（二番階層）のメソッドです。
	 *  @param aModel モデル
	 * 
	 */
	public void arrange(ForestModel aModel) {
		Integer counter = 0;
		for(Node aNode: this.nodes){
			Integer height = aNode.getExtent().height + Constants.Margin.y + Constants.Interval.y;
			aNode.setStatus(Constants.UnVisited);
			aNode.setLocation(new Point(0, height*counter++));
		}

		Point aPoint = new Point(0, 0);
		ArrayList<Node> rootNodes = this.rootNodes();
		for(Node aNode: rootNodes){
			Dimension aDimension = this.arrange(aNode, aPoint, aModel);
			aPoint = new Point(0, aDimension.height  + Constants.Interval.y);
		}

		this.flushBounds();

		return;

	}

	/**
	 *  樹状整列する再帰レベル（N番階層）のメソッドです。
	 *  @param aNode ノード（このノードから再帰的にたどって下位のものたちも整列する）
	 *  @param aPoint ノードの位置（座標）
	 *  @param aModel モデル（nullのときはアニメーションを行わない）
	 *  @return 樹状整列に必要だった大きさ（幅と高さ）
	 * 
	 */
	protected Dimension arrange(Node aNode, Point aPoint, ForestModel aModel) {
		aNode.setStatus(Constants.Visited);
		aNode.setLocation(aPoint);
		this.propagate(aModel);

		Dimension extent = aNode.getExtent();
		ArrayList<Node> subNodes = this.subNodes(aNode);
		if (subNodes.isEmpty()) {
			Integer width = aPoint.x + extent.width;
			Integer height = aPoint.y + extent.height;
			extent = new Dimension(width, height);

			return extent;
		}

		Integer width = aPoint.x + extent.width;
		Integer height = aPoint.y;
		Integer x = width + Constants.Interval.x;
		Integer y = height;
		Integer top = height;

		for (Node subNode: subNodes) {
			if (subNode.getStatus() == Constants.UnVisited) {
				extent = this.arrange(subNode, new Point(x, y), aModel);
				Integer h = y + subNode.getExtent().height;
				y = extent.height > h ? extent.height : h;
				width = extent.width> width ? extent.width : width;
				height = extent.height > height ? extent.height : height;
				y = y + Constants.Interval.y;
			}
		}

		y = y - Constants.Interval.y;
		Integer h = aNode.getExtent().height;
		if (y > (aPoint.y + h)) {
			y = top + ((y - top - h) / 2);
			aNode.setLocation(new Point(aPoint.x, y));
			this.propagate(aModel);
		}
		height = height > h ? height : h;
		extent = new Dimension(width, height);

		return extent;
	}

	/**
	 *  フォレスト（木・林・森・亜格子状の森）の領域（矩形）を応答するメソッドです。
	 *  @return aGraphics フォレスト領域（矩形）
	 * 
	 */
	public Rectangle bounds() {
		if (this.bounds == null) this.bounds = new Rectangle();
		this.nodes.forEach(aNode -> this.bounds.add(aNode.getBounds()));

		return this.bounds;
	}

	/**
	 *  フォレスト（木・林・森・亜格子状の森）を描画するメソッドです。
	 *  @param aGraphics グラフィクス（描画コンテクスト）
	 * 
	 */
	public void draw(Graphics aGraphics) {
		this.branches.forEach( aBranch -> aBranch.draw(aGraphics) );
		this.nodes.forEach( aNode -> aNode.draw(aGraphics) );
	}

	/**
	 *  フォレスト（木・林・森・亜格子状の森）の領域（矩形）を水に流す（チャラにする）メソッドです。
	 * 
	 */
	public void flushBounds() {
		this.bounds = null;
	}

	/**
	 *  チックタックの間、スリープし、モデルが変化した、と騒ぐ（広める：放送する）メソッドです。
	 *  @param aModel モデル
	 * 
	 */
	protected void propagate(ForestModel aModel) {
		if(!(aModel == null)) {
			try {
				aModel.changed();
				Thread.sleep(Constants.SleepTick);
			} catch (Exception anException) { anException.printStackTrace(); }
			this.flushBounds();
			aModel.changed();
		}
		
		return;
	}

	/**
	 *  フォレストの根元（ルート）となるノード群を応答するメソッドです。
	 *  @return ルートノード群
	 * 
	 */
	public ArrayList<Node> rootNodes() {
		// ルートノード以外の集合を集める
		ArrayList<Node> endList = new ArrayList<>();
		this.branches.forEach(aBranch -> endList.add(aBranch.end()));

		ArrayList<Node> roots = new ArrayList<>();
		this.nodes.stream()
			.filter(aNode -> !endList.contains(aNode))
			.forEach(roots::add);

		return roots;
	}

	/**
	 *  引数で指定されたノード群をノード名でソート（並び替えを）するメソッドです。
	 *  @param nodeCollection ノード群
	 *  @return ソートされたノード群
	 * 
	 */
	protected ArrayList<Node> sortNodes(ArrayList<Node> nodeCollection) {
		Collections.sort(nodeCollection);
		return nodeCollection;
	}

	/**
	 *  引数で指定されたノードのサブノード群を応答するメソッドです。
	 *  @param aNode ノード
	 *  @return サブノード群
	 * 
	 */
	public ArrayList<Node> subNodes(Node aNode) {
		ArrayList<Node> nodeList = new ArrayList<>();
		for(Branch aBranch: this.branches){
			if(aBranch.start().equals(aNode)) nodeList.add(aBranch.end());
		}

		return nodeList;
	}

	/**
	 *  引数で指定されたノードのスーパーノード群を応答するメソッドです。
	 *  @param aNode ノード
	 *  @return スーパーノード群
	 * 
	 */
	public ArrayList<Node> superNodes(Node aNode) {
		ArrayList<Node> nodeList = new ArrayList<Node>();
		for(Branch aBranch: this.branches){
			if(aBranch.end().equals(aNode)) nodeList.add(aBranch.start());
		}
		return nodeList;
	}

	/**
	 *  自分自身を文字列に変換するメソッドです。
	 *  @return 自分自身を表す文字列
	 * 
	 */
	public String toString() {
		Class<?> aClass = this.getClass();

		StringBuffer aBuffer = new StringBuffer();
		aBuffer.append(aClass.getName());
		aBuffer.append("[bounds=");
		aBuffer.append(this.bounds);
		aBuffer.append(", nodes=");
		aBuffer.append(this.nodes);
		aBuffer.append(", branches=");
		aBuffer.append(this.branches);
		aBuffer.append("]");
		return null;
	}

	/**
	 *  指定された位置（座標）にノードが存在するかを調べるメソッドです。
	 *  @param aPoint 位置（モデル座標）
	 *  @return ノード、もしも見つからなかった場合には、nullを応答します。
	 * 
	 */
	public Node whichOfNodes(Point aPoint) {
		for(Node aNode: this.nodes){
			Rectangle aRectangle = aNode.getBounds();
			if(aRectangle.contains(aPoint)) return aNode;
		}
		return null;
	}

}
