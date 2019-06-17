package forest;

import mvc.Model;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

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
		super();
		this.forest = new Forest();
		this.read(aFile);
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
		super.changed();
	}

	/**
	 *  樹状整列それ自身を応答するメソッドです。
	 *  @return 樹状整列それ自身
	 * 
	 */
	public Forest forest() {
		return forest;
	}

	/**
	 *  樹状整列データファイルから樹状整列それ自身を生成するメソッドです。
	 *  @param aFile 樹状整列データファイル
	 * 
	 */
	protected void read(File aFile) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(aFile));
			String str;
			Integer previousValue = null;
			Integer processFlag = 0;
			ArrayList<Node> nodeLists = new ArrayList<Node>();

			while ((str = reader.readLine()) != null) {
				String[] strList = str.split(" ");

				//treesの処理。デバック用出力
				if ( processFlag == 0 && strList[0].equals("|--") ) {
					//何もしない
				}
				else if ( processFlag == 1 && strList.length > 1) {
					Node aNode = new Node(strList[strList.length - 1]);
					forest.addNode(aNode);
					nodeLists.add(aNode);
				}
				else if ( processFlag == 2 && strList.length > 1) {
					Node startNode = nodeLists.get( Integer.parseInt(strList[0].replaceAll(",", "")) - 1);	//fileには1始めで数字が書かれてる
					Node endNode = nodeLists.get( Integer.parseInt(strList[1]) - 1);
					Branch aBranch = new Branch(startNode, endNode);
					forest.addBranch(aBranch);
				}

				

				switch ( str ) {
					case "tree:":
						processFlag = 0;
						break;
					case "nodes:":
						processFlag = 1;
						break;
					case "branches:":
						processFlag = 2;
						break;
					default:
						break;
				}
			}


		} catch(IOException e) {
			e.printStackTrace();
		}
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
		return forest.rootNodes();
	}

}
