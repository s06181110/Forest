package forest;

import java.awt.Component;
import java.awt.Point;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *  樹状整列におけるノード（節）を担うクラスになります。
 * 
 */
@SuppressWarnings("serial")
public class Node extends Component {

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
	private Point extent;

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

	}

	/**
	 *  ノード（節）を描画するメソッドです。
	 *  @param aGraphics グラフィクス（描画コンテクスト）
	 * 
	 */
	public void draw(Graphics aGraphics) {

	}

	/**
	 *  ノード（節）の描画領域を応答するメソッドです。
	 *  @return ノード（節）の描画領域（Rectangleのインスタンス）
	 * 
	 */
	public Rectangle getBounds() {
		return null;
	}

	/**
	 *  ノード（節）の大きさを応答するメソッドです。
	 *  @return ノード（節）の大きさ（幅と高さ）
	 * 
	 */
	public Point getExtent() {
		return null;
	}

	/**
	 *  ノード（節）の位置を応答するメソッドです。
	 *  @return ノード（節）の位置（座標）
	 * 
	 */
	public Point getLocation() {
		return null;
	}

	/**
	 *  ノード（節）の名前を応答するメソッドです。
	 *  @return ノード名（ラベル文字列）
	 * 
	 */
	public String getName() {
		return null;
	}

	/**
	 *  ノード（節）の状態を応答するメソッドです。
	 *  @return ノードの状態
	 * 
	 */
	public Integer getStatus() {
		return null;
	}

	/**
	 *  ノード（節）の大きさを設定するメソッドです。
	 *  @param aPoint ノードの大きさ（幅と高さ）
	 * 
	 */
	public void setExtent(Point aPoint) {

	}

	/**
	 *  ノード（節）の位置を設定するメソッドです。
	 *  @param aPoint ノードの位置（座標）
	 * 
	 */
	public void setLocation(Point aPoint) {

	}

	/**
	 *  ノード（節）の名前を設定するメソッドです。
	 *  @param aString ノードの名前（ラベル）
	 * 
	 */
	public void setName(String aString) {

	}

	/**
	 *  ノード（節）の状態を設定するメソッドです。
	 *  @param anInteger ノードの状態
	 * 
	 */
	public void setStatus(Integer anInteger) {

	}

	/**
	 *  文字列の高さを応答するメソッドです。
	 *  @param string 文字列
	 *  @return 文字列の高さ
	 * 
	 */
	protected int stringHeight(String string) {
		return 0;
	}

	/**
	 *  文字列の幅を応答するメソッドです。
	 *  @param string 文字列
	 *  @return 文字列の幅
	 * 
	 */
	protected int stringWidth(String string) {
		return 0;
	}

	/**
	 *  自分自身を文字列に変換するメソッドです。
	 *  @return 自分自身を表す文字列
	 * 
	 */
	public String toString() {
		return null;
	}

}
