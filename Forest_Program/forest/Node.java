package forest;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *  樹状整列におけるノード（節）を担うクラスになります。
 * 
 */
@SuppressWarnings("serial")
public class Node extends Component implements Comparable<Node>{

	/**
	 *  ノード名：ラベル文字列を記憶するフィールドです。
	 * 
	 */
	private String name;

	/**
	 *  ノードの場所（位置：座標）を記憶するフィールドです。
	 * 
	 */
	private Point location;

	/**
	 *  ノードの大きさ（幅と高さ）を記憶するフィールドです。
	 * 
	 */
	private Dimension extent;

	/**
	 *  樹状整列する際のノードの状態を記憶するフィールドです。
	 * 
	 */
	private Integer status;

	/**
	 *  このクラスのインスタンスを生成するコンストラクタです。
	 *  @param aString ノード名：ラベル文字列
	 * 
	 */
	public Node(String aString) {
		this.name = aString;
		this.location = new Point();
		Dimension aDimension = new Dimension(this.stringWidth(aString), this.stringHeight(aString));
		this.setExtent(aDimension);;
		this.status = Constants.UnKnown;
		return;
	}

	/**
	 *  ノード（節）を描画するメソッドです。
	 *  @param aGraphics グラフィクス（描画コンテクスト）
	 * 
	 */
	public void draw(Graphics aGraphics) {
		aGraphics.setColor(Constants.ForegroundColor);
		FontMetrics aFontMetrics = aGraphics.getFontMetrics();
		aGraphics.drawRect(this.location.x, this.location.y, this.extent.width+Constants.Margin.x, this.extent.height+Constants.Margin.y);
		aGraphics.drawString(this.name, this.location.x+Constants.Margin.x/2, this.location.y + Constants.Margin.y + aFontMetrics.getAscent());
		return;
	}

	/**
	 *  ノード（節）の描画領域を応答するメソッドです。
	 *  @return ノード（節）の描画領域（Rectangleのインスタンス）
	 * 
	 */
	public Rectangle getBounds() {
		Rectangle aRectangle = new Rectangle(this.location, this.extent);
		return aRectangle;
	}

	/**
	 *  ノード（節）の大きさを応答するメソッドです。
	 *  @return ノード（節）の大きさ（幅と高さ）
	 * 
	 */
	public Dimension getExtent() {
		return this.extent;
	}

	/**
	 *  ノード（節）の位置を応答するメソッドです。
	 *  @return ノード（節）の位置（座標）
	 * 
	 */
	public Point getLocation() {
		return this.location;
	}

	/**
	 *  ノード（節）の名前を応答するメソッドです。
	 *  @return ノード名（ラベル文字列）
	 * 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 *  ノード（節）の状態を応答するメソッドです。
	 *  @return ノードの状態
	 * 
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 *  ノード（節）の大きさを設定するメソッドです。
	 *  @param aPoint ノードの大きさ（幅と高さ）
	 * 
	 */
	public void setExtent(Dimension aDimension) {
		this.extent = aDimension;
		return;
	}

	/**
	 *  ノード（節）の位置を設定するメソッドです。
	 *  @param aPoint ノードの位置（座標）
	 * 
	 */
	public void setLocation(Point aPoint) {
		this.location = aPoint;
		return;
	}

	/**
	 *  ノード（節）の名前を設定するメソッドです。
	 *  @param aString ノードの名前（ラベル）
	 * 
	 */
	public void setName(String aString) {
		this.name = aString;
		return;
	}

	/**
	 *  ノード（節）の状態を設定するメソッドです。
	 *  @param anInteger ノードの状態
	 * 
	 */
	public void setStatus(Integer anInteger) {
		this.status = anInteger;
		return;
	}

	/**
	 *  文字列の高さを応答するメソッドです。
	 *  @param string 文字列
	 *  @return 文字列の高さ
	 * 
	 */
	protected int stringHeight(String string) {
		FontMetrics aFontMetrics = super.getFontMetrics(Constants.DefaultFont);
		return aFontMetrics.getHeight();
	}

	/**
	 *  文字列の幅を応答するメソッドです。
	 *  @param string 文字列
	 *  @return 文字列の幅
	 * 
	 */
	protected int stringWidth(String string) {
		FontMetrics aFontMetrics = super.getFontMetrics(Constants.DefaultFont);
		return aFontMetrics.stringWidth(string);
	}

	@Override
	public int compareTo(Node aNode) {
		return this.name.compareTo(aNode.name);
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
		aBuffer.append("[name=");
		aBuffer.append(this.name);
		aBuffer.append(",location=");
		aBuffer.append(this.location);
		aBuffer.append(",extent=");
		aBuffer.append(this.extent);
		aBuffer.append(",status=");
		aBuffer.append(this.status);
		aBuffer.append("]");
		return aBuffer.toString();
	}


}
