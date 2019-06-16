package forest;

import mvc.View;
import java.awt.Graphics;
import java.awt.Point;

/**
 *  樹状整列におけるMVCのビュー（V）を担うクラスになります。
 * 
 */
@SuppressWarnings("serial")
public class ForestView extends View {

	/**
	 *  このクラスのインスタンスを生成するコンストラクタです。
	 *  @param aModel モデル（Modelのインスタンス）
	 * 
	 */
	public ForestView(ForestModel aModel) {
		super();
		this.model = aModel;
		this.model.anime();
		this.model.arrange();
		this.model.root();
		this.model.roots();
		return;
	}

	/**
	 *  このパネル（ビュー）の描画が必要になったときに動作するメソッドです。
	 *  @param aGraphics グラフィクス（描画コンテクスト）
	 * 
	 */
	public void paintComponent(Graphics aGraphics) {
		String aString = this.toString();
		int width = aString.length();
		int height = 2;
		int x = this.getX();
		int y = this.getY();
		aGraphics.drawRect(x, y, width, height);
		aGraphics.drawString( aString, x,  y + height/2);
		if(whichOfNodes(aPoint) == null){ return; }
		aGraphics.drawLine(x, y, x, y);
		return;
	}

	/**
	 *  指定された位置（座標）にノードが存在するかを調べるメソッドです。
	 *  @param aPoint 位置（ビュー座標）
	 *  @return ノード、もしも見つからなかった場合には、nullを応答します。
	 * 
	 */
	public Node whichOfNodes(Point aPoint) {
		if(aPoint == ){ return aPoint; }
		return null;
	}

}
