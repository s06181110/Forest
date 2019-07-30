package forest;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

/**
 *  樹状整列におけるブランチ（枝）を担うクラスになります。
 * 
 */
public class Branch extends Object {

	/**
	 *  ブランチ（枝）の始点となるノードを記憶するフィールドです。
	 * 
	 */
	private Node start;

	/**
	 *  ブランチ（枝）の終点となるノードを記憶するフィールドです。
	 * 
	 */
	private Node end;

	/**
	 *  このクラスのインスタンスを生成するコンストラクタです。
	 *  @param from ブランチ（枝）の始点となるノード
	 *  @param to ブランチ（枝）の終点となるノード
	 * 
	 */
	public Branch(Node from, Node to) {
		this.start = from;
		this.end = to;
	}

	/**
	 *  ブランチ（枝）を描画するメソッドです。
	 *  @param aGraphics グラフィクス（描画コンテクスト）
	 * 
	 */
	public void draw(Graphics aGraphics) {
		Point aFirstPoint = this.start.getLocation();
		Dimension aDimension = this.start.getExtent();
		int x1 = (int)aFirstPoint.getX()+aDimension.width;
		int y1 = (int)aFirstPoint.getY()+aDimension.height/2;
		Point anEndPoint = this.end.getLocation();
		aDimension = this.end.getExtent();
		int x2 = (int)anEndPoint.getX();
		int y2 = (int)anEndPoint.getY()+aDimension.height/2;
		aGraphics.setColor(Constants.ForegroundColor);
		aGraphics.drawLine(x1, y1, x2, y2);
		return;
	}

	/**
	 *  ブランチ（枝）の終点となるノードを応答するメソッドです。
	 *  @return 終点ノード
	 * 
	 */
	public Node end() {
		return this.end;
	}

	/**
	 *  ブランチ（枝）の始点となるノードを応答するメソッドです。
	 *  @return 始点ノード
	 * 
	 */
	public Node start() {
		return this.start;
	}

	/**
	 *  自分自身を文字列に変換するメソッドです。
	 *  @return 自分自身を表す文字列
	 * 
	 */
	public String toString() {
		StringBuffer aBuffer = new StringBuffer();
		Class<?> aClass = this.getClass();
		aBuffer.append(aClass.getName());
		aBuffer.append("[start=");
		aBuffer.append(this.start.getName());
		aBuffer.append(",end=");
		aBuffer.append(this.end.getName());
		aBuffer.append("]");
		return aBuffer.toString();
	}

}
