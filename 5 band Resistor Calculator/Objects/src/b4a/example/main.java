package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class main extends Activity implements B4AActivity{
	public static main mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.main");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (main).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (main) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (main) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return main.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (main) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (main) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            main mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (main) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }

public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner1 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner2 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner3 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner4 = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinner5 = null;
public anywheresoftware.b4a.objects.collections.List _item = null;
public anywheresoftware.b4a.objects.collections.List _colornames = null;
public anywheresoftware.b4a.objects.collections.List _digitvalues = null;
public anywheresoftware.b4a.objects.collections.List _multipliervalues = null;
public anywheresoftware.b4a.objects.collections.List _colorvalues = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel2 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel3 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel4 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel5 = null;
public anywheresoftware.b4a.objects.PanelWrapper _panel6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label1 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label2 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label3 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label4 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label5 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label6 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label7 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label8 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label9 = null;
public anywheresoftware.b4a.objects.LabelWrapper _label = null;
public b4a.example.b4xgifview _b4xgifview1 = null;
public static boolean _isclick = false;
public b4a.example.dateutils _dateutils = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
anywheresoftware.b4a.keywords.constants.TypefaceWrapper _tf = null;
 //BA.debugLineNum = 55;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 56;BA.debugLine="Activity.LoadLayout(\"Layout\")";
mostCurrent._activity.LoadLayout("Layout",mostCurrent.activityBA);
 //BA.debugLineNum = 57;BA.debugLine="Dim tf As Typeface = Typeface.LoadFromAssets(\"coo";
_tf = new anywheresoftware.b4a.keywords.constants.TypefaceWrapper();
_tf = (anywheresoftware.b4a.keywords.constants.TypefaceWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.keywords.constants.TypefaceWrapper(), (android.graphics.Typeface)(anywheresoftware.b4a.keywords.Common.Typeface.LoadFromAssets("coolvetica.ttf")));
 //BA.debugLineNum = 58;BA.debugLine="Label.Typeface = tf";
mostCurrent._label.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 59;BA.debugLine="Label1.Typeface =  tf";
mostCurrent._label1.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 60;BA.debugLine="Label2.Typeface = tf";
mostCurrent._label2.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 61;BA.debugLine="Label3.Typeface = tf";
mostCurrent._label3.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 62;BA.debugLine="Label4.Typeface = tf";
mostCurrent._label4.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 63;BA.debugLine="Label5.Typeface = tf";
mostCurrent._label5.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 64;BA.debugLine="Label6.Typeface = tf";
mostCurrent._label6.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 65;BA.debugLine="Label7.Typeface = tf";
mostCurrent._label7.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 66;BA.debugLine="Label8.Typeface = tf";
mostCurrent._label8.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 67;BA.debugLine="Label9.Typeface = tf";
mostCurrent._label9.setTypeface((android.graphics.Typeface)(_tf.getObject()));
 //BA.debugLineNum = 68;BA.debugLine="item.Initialize";
mostCurrent._item.Initialize();
 //BA.debugLineNum = 69;BA.debugLine="Spinner1.DropdownBackgroundColor = 0xffD3D3D3";
mostCurrent._spinner1.setDropdownBackgroundColor(((int)0xffd3d3d3));
 //BA.debugLineNum = 70;BA.debugLine="Spinner2.DropdownBackgroundColor = 0xffD3D3D3";
mostCurrent._spinner2.setDropdownBackgroundColor(((int)0xffd3d3d3));
 //BA.debugLineNum = 71;BA.debugLine="Spinner3.DropdownBackgroundColor = 0xffD3D3D3";
mostCurrent._spinner3.setDropdownBackgroundColor(((int)0xffd3d3d3));
 //BA.debugLineNum = 72;BA.debugLine="Spinner4.DropdownBackgroundColor = 0xffD3D3D3";
mostCurrent._spinner4.setDropdownBackgroundColor(((int)0xffd3d3d3));
 //BA.debugLineNum = 73;BA.debugLine="Spinner5.DropdownBackgroundColor = 0xffD3D3D3";
mostCurrent._spinner5.setDropdownBackgroundColor(((int)0xffd3d3d3));
 //BA.debugLineNum = 74;BA.debugLine="ColorNames.Initialize";
mostCurrent._colornames.Initialize();
 //BA.debugLineNum = 75;BA.debugLine="DigitValues.Initialize";
mostCurrent._digitvalues.Initialize();
 //BA.debugLineNum = 76;BA.debugLine="MultiplierValues.Initialize";
mostCurrent._multipliervalues.Initialize();
 //BA.debugLineNum = 77;BA.debugLine="ColorValues.Initialize";
mostCurrent._colorvalues.Initialize();
 //BA.debugLineNum = 78;BA.debugLine="ColorNames.AddAll(Array As String(\"black\",\"brown\"";
mostCurrent._colornames.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"black","brown","red","orange","yellow","green","blue","violet","gray","white","gold","silver"}));
 //BA.debugLineNum = 79;BA.debugLine="DigitValues.AddAll(Array As Int(0,1,2,3,4,5,6,7,8";
mostCurrent._digitvalues.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new int[]{(int) (0),(int) (1),(int) (2),(int) (3),(int) (4),(int) (5),(int) (6),(int) (7),(int) (8),(int) (9),(int) (-1),(int) (-1)}));
 //BA.debugLineNum = 80;BA.debugLine="MultiplierValues.AddAll(Array As Double(1,10,100,";
mostCurrent._multipliervalues.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new double[]{1,10,100,1000,10000,100000,1000000,10000000,100000000,1000000000,0.1,0.01}));
 //BA.debugLineNum = 81;BA.debugLine="ColorValues.AddAll(Array As Int( _ 		Colors.Black";
mostCurrent._colorvalues.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new int[]{anywheresoftware.b4a.keywords.Common.Colors.Black,((int)0xff964b00),anywheresoftware.b4a.keywords.Common.Colors.Red,((int)0xffffa500),anywheresoftware.b4a.keywords.Common.Colors.Yellow,anywheresoftware.b4a.keywords.Common.Colors.Green,anywheresoftware.b4a.keywords.Common.Colors.Blue,((int)0xff7f00ff),anywheresoftware.b4a.keywords.Common.Colors.Gray,anywheresoftware.b4a.keywords.Common.Colors.White,((int)0xffffd700),((int)0xff999b9b)}));
 //BA.debugLineNum = 94;BA.debugLine="item.AddAll(Array As String(\"Black\", \"Brown\", \"Re";
mostCurrent._item.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"Black","Brown","Red","Orange","Yellow","Green","Blue","Violet","Gray","White"}));
 //BA.debugLineNum = 95;BA.debugLine="Spinner1.AddAll(item)";
mostCurrent._spinner1.AddAll(mostCurrent._item);
 //BA.debugLineNum = 96;BA.debugLine="Spinner2.AddAll(item)";
mostCurrent._spinner2.AddAll(mostCurrent._item);
 //BA.debugLineNum = 97;BA.debugLine="Spinner3.AddAll(item)";
mostCurrent._spinner3.AddAll(mostCurrent._item);
 //BA.debugLineNum = 98;BA.debugLine="Spinner4.AddAll(Array As String(\"x1 Black\", \"x10";
mostCurrent._spinner4.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"x1 Black","x10 Brown","x100 Red","x1k Orange","x10k Yellow","x100k Green","x1M Blue","x10M Violet","x100M Gray","x1G White","x0.1 Gold","x0.01 Silver"}));
 //BA.debugLineNum = 99;BA.debugLine="Spinner5.AddAll(Array As String(\"±1% Brown\", \"±2%";
mostCurrent._spinner5.AddAll(anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{"±1% Brown","±2% Red","±3% Orange","±4% Yellow","±0.5% Green","±0.25% Blue","±0.10% Violet","±0.05% Gray","±5% Gold","±10% Silver"}));
 //BA.debugLineNum = 100;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 106;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 108;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 102;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 104;BA.debugLine="End Sub";
return "";
}
public static String  _button1_click() throws Exception{
int _d1 = 0;
int _d2 = 0;
int _d3 = 0;
double _multiplier = 0;
double _resistance = 0;
 //BA.debugLineNum = 110;BA.debugLine="Sub Button1_Click";
 //BA.debugLineNum = 111;BA.debugLine="Dim d1 As Int = GetDigit(Spinner1.SelectedItem)";
_d1 = _getdigit(mostCurrent._spinner1.getSelectedItem());
 //BA.debugLineNum = 112;BA.debugLine="Dim d2 As Int = GetDigit(Spinner2.SelectedItem)";
_d2 = _getdigit(mostCurrent._spinner2.getSelectedItem());
 //BA.debugLineNum = 113;BA.debugLine="Dim d3 As Int = GetDigit(Spinner3.SelectedItem)";
_d3 = _getdigit(mostCurrent._spinner3.getSelectedItem());
 //BA.debugLineNum = 114;BA.debugLine="Dim multiplier As Double = GetMultiplier(Spinner4";
_multiplier = _getmultiplier(mostCurrent._spinner4.getSelectedItem());
 //BA.debugLineNum = 116;BA.debugLine="Dim resistance As Double";
_resistance = 0;
 //BA.debugLineNum = 117;BA.debugLine="resistance = (d1 * 100 + d2 * 10 + d3) * multipli";
_resistance = (_d1*100+_d2*10+_d3)*_multiplier;
 //BA.debugLineNum = 119;BA.debugLine="Label6.Text = d1 & d2 & d3";
mostCurrent._label6.setText(BA.ObjectToCharSequence(BA.NumberToString(_d1)+BA.NumberToString(_d2)+BA.NumberToString(_d3)));
 //BA.debugLineNum = 120;BA.debugLine="Label7.Text = Spinner4.SelectedItem.SubString2(0,";
mostCurrent._label7.setText(BA.ObjectToCharSequence(mostCurrent._spinner4.getSelectedItem().substring((int) (0),mostCurrent._spinner4.getSelectedItem().indexOf(" "))+"Ω"));
 //BA.debugLineNum = 121;BA.debugLine="Label8.Text = Spinner5.SelectedItem.SubString2(0,";
mostCurrent._label8.setText(BA.ObjectToCharSequence(mostCurrent._spinner5.getSelectedItem().substring((int) (0),(int) (mostCurrent._spinner5.getSelectedItem().indexOf("%")+1))));
 //BA.debugLineNum = 122;BA.debugLine="Label9.Text = FormatResistance(resistance) & \" \"";
mostCurrent._label9.setText(BA.ObjectToCharSequence(_formatresistance(_resistance)+" "+mostCurrent._spinner5.getSelectedItem().substring((int) (0),(int) (mostCurrent._spinner5.getSelectedItem().indexOf("%")+1))));
 //BA.debugLineNum = 123;BA.debugLine="End Sub";
return "";
}
public static String  _formatresistance(double _value) throws Exception{
 //BA.debugLineNum = 125;BA.debugLine="Private Sub FormatResistance(value As Double) As S";
 //BA.debugLineNum = 126;BA.debugLine="If value >= 1000000000 Then";
if (_value>=1000000000) { 
 //BA.debugLineNum = 127;BA.debugLine="Return NumberFormat(value / 1000000000, 1, 2) &";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat(_value/(double)1000000000,(int) (1),(int) (2))+" GΩ";
 }else if(_value>=1000000) { 
 //BA.debugLineNum = 129;BA.debugLine="Return NumberFormat(value / 1000000, 1, 2) & \" M";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat(_value/(double)1000000,(int) (1),(int) (2))+" MΩ";
 }else if(_value>=1000) { 
 //BA.debugLineNum = 131;BA.debugLine="Return NumberFormat(value / 1000, 1, 2) & \" kΩ\"";
if (true) return anywheresoftware.b4a.keywords.Common.NumberFormat(_value/(double)1000,(int) (1),(int) (2))+" kΩ";
 }else {
 //BA.debugLineNum = 133;BA.debugLine="Return value & \" Ω\"";
if (true) return BA.NumberToString(_value)+" Ω";
 };
 //BA.debugLineNum = 135;BA.debugLine="End Sub";
return "";
}
public static int  _getcolor(String _name) throws Exception{
int _i = 0;
 //BA.debugLineNum = 163;BA.debugLine="Private Sub GetColor(name As String) As Int";
 //BA.debugLineNum = 164;BA.debugLine="Dim i As Int = GetIndex(name)";
_i = _getindex(_name);
 //BA.debugLineNum = 165;BA.debugLine="Return ColorValues.Get(i)";
if (true) return (int)(BA.ObjectToNumber(mostCurrent._colorvalues.Get(_i)));
 //BA.debugLineNum = 166;BA.debugLine="End Sub";
return 0;
}
public static int  _getdigit(String _name) throws Exception{
int _i = 0;
 //BA.debugLineNum = 151;BA.debugLine="Private Sub GetDigit(name As String) As Int";
 //BA.debugLineNum = 152;BA.debugLine="Dim i As Int = GetIndex(name)";
_i = _getindex(_name);
 //BA.debugLineNum = 153;BA.debugLine="Return DigitValues.Get(i)";
if (true) return (int)(BA.ObjectToNumber(mostCurrent._digitvalues.Get(_i)));
 //BA.debugLineNum = 154;BA.debugLine="End Sub";
return 0;
}
public static int  _getindex(String _name) throws Exception{
int _i = 0;
 //BA.debugLineNum = 138;BA.debugLine="Private Sub GetIndex(name As String) As Int";
 //BA.debugLineNum = 139;BA.debugLine="name = name.ToLowerCase";
_name = _name.toLowerCase();
 //BA.debugLineNum = 141;BA.debugLine="For i = 0 To ColorNames.Size - 1";
{
final int step2 = 1;
final int limit2 = (int) (mostCurrent._colornames.getSize()-1);
_i = (int) (0) ;
for (;_i <= limit2 ;_i = _i + step2 ) {
 //BA.debugLineNum = 142;BA.debugLine="If name.Contains(ColorNames.Get(i)) Then";
if (_name.contains(BA.ObjectToString(mostCurrent._colornames.Get(_i)))) { 
 //BA.debugLineNum = 143;BA.debugLine="Return i";
if (true) return _i;
 };
 }
};
 //BA.debugLineNum = 147;BA.debugLine="Return 0";
if (true) return (int) (0);
 //BA.debugLineNum = 148;BA.debugLine="End Sub";
return 0;
}
public static double  _getmultiplier(String _name) throws Exception{
int _i = 0;
 //BA.debugLineNum = 157;BA.debugLine="Private Sub GetMultiplier(name As String) As Doubl";
 //BA.debugLineNum = 158;BA.debugLine="Dim i As Int = GetIndex(name)";
_i = _getindex(_name);
 //BA.debugLineNum = 159;BA.debugLine="Return MultiplierValues.Get(i)";
if (true) return (double)(BA.ObjectToNumber(mostCurrent._multipliervalues.Get(_i)));
 //BA.debugLineNum = 160;BA.debugLine="End Sub";
return 0;
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private Spinner1 As Spinner";
mostCurrent._spinner1 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private Spinner2 As Spinner";
mostCurrent._spinner2 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private Spinner3 As Spinner";
mostCurrent._spinner3 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private Spinner4 As Spinner";
mostCurrent._spinner4 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private Spinner5 As Spinner";
mostCurrent._spinner5 = new anywheresoftware.b4a.objects.SpinnerWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private item As List";
mostCurrent._item = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 30;BA.debugLine="Private ColorNames As List";
mostCurrent._colornames = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 31;BA.debugLine="Private DigitValues As List";
mostCurrent._digitvalues = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 32;BA.debugLine="Private MultiplierValues As List";
mostCurrent._multipliervalues = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 33;BA.debugLine="Private ColorValues As List";
mostCurrent._colorvalues = new anywheresoftware.b4a.objects.collections.List();
 //BA.debugLineNum = 35;BA.debugLine="Private Panel2 As Panel";
mostCurrent._panel2 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 36;BA.debugLine="Private Panel3 As Panel";
mostCurrent._panel3 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 37;BA.debugLine="Private Panel4 As Panel";
mostCurrent._panel4 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 38;BA.debugLine="Private Panel5 As Panel";
mostCurrent._panel5 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 39;BA.debugLine="Private Panel6 As Panel";
mostCurrent._panel6 = new anywheresoftware.b4a.objects.PanelWrapper();
 //BA.debugLineNum = 40;BA.debugLine="Private Label1 As Label";
mostCurrent._label1 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 41;BA.debugLine="Private Label2 As Label";
mostCurrent._label2 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 42;BA.debugLine="Private Label3 As Label";
mostCurrent._label3 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 43;BA.debugLine="Private Label4 As Label";
mostCurrent._label4 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 44;BA.debugLine="Private Label5 As Label";
mostCurrent._label5 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 46;BA.debugLine="Private Label6 As Label";
mostCurrent._label6 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 47;BA.debugLine="Private Label7 As Label";
mostCurrent._label7 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 48;BA.debugLine="Private Label8 As Label";
mostCurrent._label8 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 49;BA.debugLine="Private Label9 As Label";
mostCurrent._label9 = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 50;BA.debugLine="Private Label As Label";
mostCurrent._label = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 51;BA.debugLine="Private B4XGifView1 As B4XGifView";
mostCurrent._b4xgifview1 = new b4a.example.b4xgifview();
 //BA.debugLineNum = 52;BA.debugLine="Private isclick As Boolean";
_isclick = false;
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _listview1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 188;BA.debugLine="Private Sub ListView1_ItemClick (Position As Int,";
 //BA.debugLineNum = 190;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        b4a.example.dateutils._process_globals();
main._process_globals();
starter._process_globals();
xuiviewsutils._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return "";
}
public static String  _spinner1_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 176;BA.debugLine="Private Sub Spinner1_ItemClick (Position As Int, V";
 //BA.debugLineNum = 177;BA.debugLine="Panel2.Color = GetColor(Value)";
mostCurrent._panel2.setColor(_getcolor(BA.ObjectToString(_value)));
 //BA.debugLineNum = 178;BA.debugLine="End Sub";
return "";
}
public static String  _spinner2_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 172;BA.debugLine="Private Sub Spinner2_ItemClick (Position As Int, V";
 //BA.debugLineNum = 173;BA.debugLine="Panel3.Color = GetColor(Value)";
mostCurrent._panel3.setColor(_getcolor(BA.ObjectToString(_value)));
 //BA.debugLineNum = 174;BA.debugLine="End Sub";
return "";
}
public static String  _spinner3_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 168;BA.debugLine="Private Sub Spinner3_ItemClick (Position As Int, V";
 //BA.debugLineNum = 169;BA.debugLine="Panel4.Color = GetColor(Value)";
mostCurrent._panel4.setColor(_getcolor(BA.ObjectToString(_value)));
 //BA.debugLineNum = 170;BA.debugLine="End Sub";
return "";
}
public static String  _spinner4_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 184;BA.debugLine="Private Sub Spinner4_ItemClick (Position As Int, V";
 //BA.debugLineNum = 185;BA.debugLine="Panel5.Color = GetColor(Value)";
mostCurrent._panel5.setColor(_getcolor(BA.ObjectToString(_value)));
 //BA.debugLineNum = 186;BA.debugLine="End Sub";
return "";
}
public static String  _spinner5_itemclick(int _position,Object _value) throws Exception{
 //BA.debugLineNum = 180;BA.debugLine="Private Sub Spinner5_ItemClick (Position As Int, V";
 //BA.debugLineNum = 181;BA.debugLine="Panel6.Color = GetColor(Value)";
mostCurrent._panel6.setColor(_getcolor(BA.ObjectToString(_value)));
 //BA.debugLineNum = 182;BA.debugLine="End Sub";
return "";
}
}
