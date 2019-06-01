package forest;

import mvc.Model;
import java.io.File;
import java.util.ArrayList;

/**
 *  樹状整列におけるMVCのモデル（M）を担うクラスになります。
 * 
 */
public class ForestModel extends Model {

	/**
	 *  樹状整列それ自身を記憶しておくフィールドです。
	 * 
	 */
	private Forest forest;

	/**
	 *  このクラスのインスタンスを生成するコンストラクタです。
	 *  @param aFile 樹状整列データファイル
	 * 
	 */
	public ForestModel(File aFile) {

	}

	/**
	 *  アニメーションを行うメソッドです。
	 * 
	 */
	public void animate() {

	}

	/**
	 *  樹状整列を行うメソッドです。
	 * 
	 */
	public void arrange() {

	}

	/**
	 *  自分自身が変化したことを依存物たちに放送（updateを依頼）するメソッドです。
	 * 
	 */
	public void changed() {

	}

	/**
	 *  樹状整列それ自身を応答するメソッドです。
	 *  @return 樹状整列それ自身
	 * 
	 */
	public Forest forest() {
		return null;
	}

	/**
	 *  樹状整列データファイルから樹状整列それ自身を生成するメソッドです。
	 *  @param aFile 樹状整列データファイル
	 * 
	 */
	protected void read(File aFile) {

	}

	/**
	 *  樹状整列の根元（ルート）になるノードを探し出して応答するメソッドです。
	 *  @return ルートノード、ただし、見つからないときはnull
	 * 
	 */
	public Node root() {
		return null;
	}

	/**
	 *  樹状整列の根元（ルート）になるノードたちを探し出して応答するメソッドです。
	 *  @return ルートノードたち、ただし、見つからないときは空リスト
	 * 
	 */
	public ArrayList<Node> roots() {
		return null;
	}

}
