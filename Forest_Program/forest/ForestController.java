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

	/**
	 * 情報を握っているModelのインスタンスを束縛する。
	 */
	protected Model model;

	/**
	 * 表示を司るViewのインスタンスを束縛する。
	 */
	protected View view;

	/**
	 * 以前にマウスのボタンが押下された場所をPointのインスタンスとして束縛する。
	 */
	private Point previous;

	/**
	 * 現在にマウスのボタンが押下された場所をPointのインスタンスとして束縛する。
	 */
	private Point current;

	/**
	 * インスタンスを生成して応答する。
	 * すべてのインスタンス変数（model, view, previous, current）をnull化する。
	 */
	public ForestController()
	{
		super();
		this.model = null;
		this.view = null;
		this.previous = null;
		this.current = null;
		return;
	}

	/**
	 *  マウスのボタンをクリックしたときに動作するメソッドです。
	 *  クリックした位置からノードを割り出します。
	 *  @param aMouseEvent マウスイベント
	 * 
	 */
	public void mouseClicked(MouseEvent aMouseEvent) { //return:void
		Point aPoint = aMouseEvent.getPoint();
		aPoint.translate(view.scrollAmount().x, view.scrollAmount().y);
		System.out.println(aPoint);
		return;
	}
	
	/**
	 * 指定されたモデルをインスタンス変数modelに設定する。
	 * @param aModel このコントローラのモデル
	 */
	public void setModel(Model aModel)
	{
		this.model = aModel;
		return;
	}

	/**
	 * 指定されたビューをインスタンス変数viewに設定し、
	 * ビューのマウスのリスナおよびモーションリスナそしてホイールリスナをこのコントローラにする。
	 * @param aView このコントローラのビュー
	 */
	public void setView(View aView)
	{
		this.view = aView;
		this.view.addMouseListener(this);
		this.view.addMouseMotionListener(this);
		this.view.addMouseWheelListener(this);
		return;
	}

}
