package forest;

import mvc.Model;
import mvc.View;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
		this.arrange();

		return;
	}

	/**
	 *  アニメーションを行うメソッドです。
	 * 
	 */
	public void animate() {
		this.forest.arrange(this);
		this.changed();

		return;
	}

	/**
	 *  樹状整列を行うメソッドです。
	 *  
	 */
	public void arrange() {
		this.forest.arrange();
		this.changed();

		return;
	}

	/**
	 *  自分自身が変化したことを依存物たちに放送（updateを依頼）するメソッドです。
	 * 
	 */
	@Override
	public void changed() {
		Rectangle aRectangle = this.forest.bounds();
		BufferedImage anImage = new BufferedImage(aRectangle.width, aRectangle.height, BufferedImage.TYPE_INT_RGB);
		this.picture(anImage);

		Graphics2D aGraphics = this.picture().createGraphics();
		// レンダリングを綺麗にする設定を施す
		aGraphics.setComposite(AlphaComposite.Src);
		aGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		aGraphics.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		aGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

		aGraphics.setColor(Constants.BackgroundColor);
		aGraphics.fillRect(0, 0, aRectangle.width, aRectangle.height);
		this.forest.draw(aGraphics);

		for(View aView: this.dependents){
			aView.update();
		}

		return;
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
			BufferedReader inputReader = new BufferedReader(new FileReader(aFile));
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
			inputReader.close();


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
		ArrayList<Node> roots = this.roots();
		return roots.isEmpty() ? null : roots.get(0);
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
