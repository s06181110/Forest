package forest;

import mvc.Model;
import java.awt.Point;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
// import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

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
		super();
		this.forest = new Forest();
		this.read(aFile);
	}

	/**
	 *  アニメーションを行うメソッドです。
	 * 
	 */
	public void animate() {
		// this.arrange();
		// this.changed();
	}

	/**
	 *  樹状整列を行うメソッドです。
	 *  
	 */
	public void arrange() {
		this.forest.arrange(this);
		return;
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
		return this.forest;
	}

	/**
	 *  樹状整列データファイルから樹状整列それ自身を生成するメソッドです。
	 *  @param aFile 樹状整列データファイル
	 * 
	 */
	protected void read(File aFile) {
		try {
			FileInputStream inputStream = new FileInputStream(aFile);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader inputReader = new BufferedReader(inputStreamReader);
			// BufferedReader reader = new BufferedReader(new FileReader(aFile));
			String str;
			Integer previousValue = null;
			ArrayList<Node> nodeLists = new ArrayList<Node>();

			Integer processFlag = 0; // [tag] tree:
			while ((str = inputReader.readLine()) != null) {
				String[] strList = str.split(" ");
				//treesの処理。デバック用出力
				if ( processFlag == 0 ) {
					//何もしない
				}
				else if ( processFlag == 1 && strList.length > 1) { 
					Node aNode = new Node(strList[strList.length - 1]);
					// nodeの初期位置として
					Point aPoint = new Point(0, 15*(Integer.valueOf(strList[0].replace(",", ""))-1));
					aNode.setLocation(aPoint);
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
					case Constants.TagOfTrees:
						processFlag = 0;
						break;
					case Constants.TagOfNodes:
						processFlag = 1;
						break;
					case Constants.TagOfBranches:
						processFlag = 2;
						break;
					default:
						break;
				}
			}


		}
		catch(FileNotFoundException anException) { anException.printStackTrace(); }
		catch(UnsupportedEncodingException anException) { anException.printStackTrace(); }
		catch(IOException anException) { anException.printStackTrace(); }

	}

	/**
	 *  樹状整列の根元（ルート）になるノードを探し出して応答するメソッドです。
	 *  @return ルートノード、ただし、見つからないときはnull
	 * 
	 */
	public Node root() {
		return this.forest.rootNodes().get(0);
	}

	/**
	 *  樹状整列の根元（ルート）になるノードたちを探し出して応答するメソッドです。
	 *  @return ルートノードたち、ただし、見つからないときは空リスト
	 * 
	 */
	public ArrayList<Node> roots() {
		return this.forest.rootNodes();
	}

}
