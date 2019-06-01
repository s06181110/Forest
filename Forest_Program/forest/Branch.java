package forest;

import java.awt.Graphics;

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

	}

	/**
	 *  ブランチ（枝）を描画するメソッドです。
	 *  @param aGraphics グラフィクス（描画コンテクスト）
	 * 
	 */
	public void draw(Graphics aGraphics) {

	}

	/**
	 *  ブランチ（枝）の終点となるノードを応答するメソッドです。
	 *  @return 終点ノード
	 * 
	 */
	public Node end() {
		return null;
	}

	/**
	 *  ブランチ（枝）の始点となるノードを応答するメソッドです。
	 *  @return 始点ノード
	 * 
	 */
	public Node start() {
		return null;
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
