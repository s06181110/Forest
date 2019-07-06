package forest;

import mvc.Controller;

import java.awt.Point;
import java.awt.event.MouseEvent;

/**
 *  樹状整列におけるMVCのコントローラ（C）を担うクラスになります。
 * 
 */
public class ForestController extends Controller {

	/**
	 *  このクラスのインスタンスを生成するコンストラクタです。
	 * 
	 */  
	public ForestController()
	{
		super();
		return;
	}

	/**
	 *  マウスのボタンをクリックしたときに動作するメソッドです。
	 *  クリックした位置からノードを割り出します。
	 *  @param aMouseEvent マウスイベント
	 * 
	 */
	@Override
	public void mouseClicked(MouseEvent aMouseEvent) {
		Point aPoint = aMouseEvent.getPoint();
		ForestView aView = (ForestView)super.view;
		Node aNode = aView.whichOfNodes(aPoint);
		if(aNode != null) System.out.println(aNode);
		return;
	}
}
