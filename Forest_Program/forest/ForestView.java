package forest;

import mvc.View;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

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
		super(aModel, new ForestController());
		return;
	}

	/**
	 *  このパネル（ビュー）の描画が必要になったときに動作するメソッドです。
	 *  @param aGraphics グラフィクス（描画コンテクスト）
	 * 
	 */
	public void paintComponent(Graphics aGraphics) {
		int width = super.getWidth();
		int height = super.getHeight();

		aGraphics.setColor(Constants.BackgroundColor);
		aGraphics.fillRect(0, 0, width, height);
		ForestModel aModel = (ForestModel)this.model;
		if (aModel == null) { return; }
		// Forest aForest = aModel.forest();
		BufferedImage picture = this.model.picture();
		if(this.model == null) return;
		Integer x = 0 - this.scrollAmount().x;
		Integer y = 0 - this.scrollAmount().y;
		// if (aForest == null) { return; }
		aGraphics.drawImage(picture, x, y, null);
		return;
	}

	/**
	 *  指定された位置（座標）にノードが存在するかを調べるメソッドです。
	 *  @param aPoint 位置（ビュー座標）
	 *  @return ノード、もしも見つからなかった場合には、nullを応答します。
	 * 
	 */
	public Node whichOfNodes(Point aPoint) {
		System.out.printf("%s\n", aPoint);
		return null;
	}

}
