package com.school.recipemanager;


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
	public static final boolean includeTitle = true;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new BA(this.getApplicationContext(), null, null, "com.school.recipemanager", "com.school.recipemanager.main");
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
		activityBA = new BA(this, layout, processBA, "com.school.recipemanager", "com.school.recipemanager.main");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "com.school.recipemanager.main", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
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
public static anywheresoftware.b4a.sql.SQL _sql1 = null;
public static int _selectedrecipeid = 0;
public anywheresoftware.b4a.objects.LabelWrapper _lblheader = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblname = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblingredients = null;
public anywheresoftware.b4a.objects.LabelWrapper _lblinstructions = null;
public anywheresoftware.b4a.objects.LabelWrapper _lbllist = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtingredients = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtinstructions = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnsave = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btndelete = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnclear = null;
public anywheresoftware.b4a.objects.ListViewWrapper _lvrecipes = null;
public com.school.recipemanager.starter _starter = null;

public static boolean isAnyActivityVisible() {
    boolean vis = false;
vis = vis | (main.mostCurrent != null);
return vis;}
public static String  _activity_create(boolean _firsttime) throws Exception{
 //BA.debugLineNum = 35;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
 //BA.debugLineNum = 36;BA.debugLine="Activity.Title = \"Recipe Manager\"";
mostCurrent._activity.setTitle(BA.ObjectToCharSequence("Recipe Manager"));
 //BA.debugLineNum = 37;BA.debugLine="Activity.Color = Colors.RGB(245, 247, 250)";
mostCurrent._activity.setColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (245),(int) (247),(int) (250)));
 //BA.debugLineNum = 38;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
 //BA.debugLineNum = 39;BA.debugLine="SetupDatabase";
_setupdatabase();
 };
 //BA.debugLineNum = 41;BA.debugLine="Activity.LoadLayout(\"layout\")";
mostCurrent._activity.LoadLayout("layout",mostCurrent.activityBA);
 //BA.debugLineNum = 42;BA.debugLine="BuildLayout";
_buildlayout();
 //BA.debugLineNum = 43;BA.debugLine="LoadRecipes";
_loadrecipes();
 //BA.debugLineNum = 44;BA.debugLine="ClearForm";
_clearform();
 //BA.debugLineNum = 45;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
 //BA.debugLineNum = 51;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
 //BA.debugLineNum = 53;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
 //BA.debugLineNum = 47;BA.debugLine="Sub Activity_Resume";
 //BA.debugLineNum = 48;BA.debugLine="LoadRecipes";
_loadrecipes();
 //BA.debugLineNum = 49;BA.debugLine="End Sub";
return "";
}
public static String  _btnclear_click() throws Exception{
 //BA.debugLineNum = 215;BA.debugLine="Sub btnClear_Click";
 //BA.debugLineNum = 216;BA.debugLine="ClearForm";
_clearform();
 //BA.debugLineNum = 217;BA.debugLine="End Sub";
return "";
}
public static String  _btndelete_click() throws Exception{
 //BA.debugLineNum = 203;BA.debugLine="Sub btnDelete_Click";
 //BA.debugLineNum = 204;BA.debugLine="If SelectedRecipeId = -1 Then";
if (_selectedrecipeid==-1) { 
 //BA.debugLineNum = 205;BA.debugLine="xui.MsgboxAsync(\"Select a recipe from the list f";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Select a recipe from the list first."),BA.ObjectToCharSequence("Nothing Selected"));
 //BA.debugLineNum = 206;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 209;BA.debugLine="SQL1.ExecNonQuery2(\"DELETE FROM recipes WHERE id";
_sql1.ExecNonQuery2("DELETE FROM recipes WHERE id = ?",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_selectedrecipeid)}));
 //BA.debugLineNum = 210;BA.debugLine="ToastMessageShow(\"Recipe deleted.\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Recipe deleted."),anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 211;BA.debugLine="LoadRecipes";
_loadrecipes();
 //BA.debugLineNum = 212;BA.debugLine="ClearForm";
_clearform();
 //BA.debugLineNum = 213;BA.debugLine="End Sub";
return "";
}
public static String  _btnsave_click() throws Exception{
String _recipename = "";
 //BA.debugLineNum = 182;BA.debugLine="Sub btnSave_Click";
 //BA.debugLineNum = 183;BA.debugLine="Dim recipeName As String = txtName.Text.Trim";
_recipename = mostCurrent._txtname.getText().trim();
 //BA.debugLineNum = 184;BA.debugLine="If recipeName = \"\" Then";
if ((_recipename).equals("")) { 
 //BA.debugLineNum = 185;BA.debugLine="xui.MsgboxAsync(\"Please enter a recipe name.\", \"";
_xui.MsgboxAsync(processBA,BA.ObjectToCharSequence("Please enter a recipe name."),BA.ObjectToCharSequence("Missing Information"));
 //BA.debugLineNum = 186;BA.debugLine="Return";
if (true) return "";
 };
 //BA.debugLineNum = 189;BA.debugLine="If SelectedRecipeId = -1 Then";
if (_selectedrecipeid==-1) { 
 //BA.debugLineNum = 190;BA.debugLine="SQL1.ExecNonQuery2(\"INSERT INTO recipes (name, i";
_sql1.ExecNonQuery2("INSERT INTO recipes (name, ingredients, instructions) VALUES (?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_recipename),(Object)(mostCurrent._txtingredients.getText().trim()),(Object)(mostCurrent._txtinstructions.getText().trim())}));
 //BA.debugLineNum = 192;BA.debugLine="ToastMessageShow(\"Recipe added.\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Recipe added."),anywheresoftware.b4a.keywords.Common.False);
 }else {
 //BA.debugLineNum = 194;BA.debugLine="SQL1.ExecNonQuery2(\"UPDATE recipes SET name = ?,";
_sql1.ExecNonQuery2("UPDATE recipes SET name = ?, ingredients = ?, instructions = ? WHERE id = ?",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_recipename),(Object)(mostCurrent._txtingredients.getText().trim()),(Object)(mostCurrent._txtinstructions.getText().trim()),(Object)(_selectedrecipeid)}));
 //BA.debugLineNum = 196;BA.debugLine="ToastMessageShow(\"Recipe updated.\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Recipe updated."),anywheresoftware.b4a.keywords.Common.False);
 };
 //BA.debugLineNum = 199;BA.debugLine="LoadRecipes";
_loadrecipes();
 //BA.debugLineNum = 200;BA.debugLine="ClearForm";
_clearform();
 //BA.debugLineNum = 201;BA.debugLine="End Sub";
return "";
}
public static String  _buildlayout() throws Exception{
 //BA.debugLineNum = 77;BA.debugLine="Private Sub BuildLayout";
 //BA.debugLineNum = 78;BA.debugLine="Activity.RemoveAllViews";
mostCurrent._activity.RemoveAllViews();
 //BA.debugLineNum = 80;BA.debugLine="lblHeader.Initialize(\"\")";
mostCurrent._lblheader.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 81;BA.debugLine="lblHeader.Text = \"Recipe Manager\"";
mostCurrent._lblheader.setText(BA.ObjectToCharSequence("Recipe Manager"));
 //BA.debugLineNum = 82;BA.debugLine="lblHeader.TextSize = 24";
mostCurrent._lblheader.setTextSize((float) (24));
 //BA.debugLineNum = 83;BA.debugLine="lblHeader.TextColor = Colors.RGB(31, 41, 55)";
mostCurrent._lblheader.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (31),(int) (41),(int) (55)));
 //BA.debugLineNum = 84;BA.debugLine="lblHeader.Typeface = Typeface.DEFAULT_BOLD";
mostCurrent._lblheader.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 85;BA.debugLine="lblHeader.Gravity = Gravity.CENTER";
mostCurrent._lblheader.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.CENTER);
 //BA.debugLineNum = 86;BA.debugLine="Activity.AddView(lblHeader, 10dip, 6dip, 100%x -";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lblheader.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (6)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (38)));
 //BA.debugLineNum = 88;BA.debugLine="lblName.Initialize(\"\")";
mostCurrent._lblname.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 89;BA.debugLine="lblName.Text = \"Recipe Name\"";
mostCurrent._lblname.setText(BA.ObjectToCharSequence("Recipe Name"));
 //BA.debugLineNum = 90;BA.debugLine="lblName.TextSize = 14";
mostCurrent._lblname.setTextSize((float) (14));
 //BA.debugLineNum = 91;BA.debugLine="lblName.TextColor = Colors.RGB(55, 65, 81)";
mostCurrent._lblname.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (55),(int) (65),(int) (81)));
 //BA.debugLineNum = 92;BA.debugLine="Activity.AddView(lblName, 10dip, 52dip, 100%x - 2";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lblname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (52)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22)));
 //BA.debugLineNum = 94;BA.debugLine="txtName.Initialize(\"txtName\")";
mostCurrent._txtname.Initialize(mostCurrent.activityBA,"txtName");
 //BA.debugLineNum = 95;BA.debugLine="txtName.Hint = \"Example: Chicken Adobo\"";
mostCurrent._txtname.setHint("Example: Chicken Adobo");
 //BA.debugLineNum = 96;BA.debugLine="txtName.SingleLine = True";
mostCurrent._txtname.setSingleLine(anywheresoftware.b4a.keywords.Common.True);
 //BA.debugLineNum = 97;BA.debugLine="txtName.TextSize = 16";
mostCurrent._txtname.setTextSize((float) (16));
 //BA.debugLineNum = 98;BA.debugLine="txtName.Color = Colors.White";
mostCurrent._txtname.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 99;BA.debugLine="Activity.AddView(txtName, 10dip, 76dip, 100%x - 2";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._txtname.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (76)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (44)));
 //BA.debugLineNum = 101;BA.debugLine="lblIngredients.Initialize(\"\")";
mostCurrent._lblingredients.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 102;BA.debugLine="lblIngredients.Text = \"Ingredients\"";
mostCurrent._lblingredients.setText(BA.ObjectToCharSequence("Ingredients"));
 //BA.debugLineNum = 103;BA.debugLine="lblIngredients.TextSize = 14";
mostCurrent._lblingredients.setTextSize((float) (14));
 //BA.debugLineNum = 104;BA.debugLine="lblIngredients.TextColor = Colors.RGB(55, 65, 81)";
mostCurrent._lblingredients.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (55),(int) (65),(int) (81)));
 //BA.debugLineNum = 105;BA.debugLine="Activity.AddView(lblIngredients, 10dip, 126dip, 1";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lblingredients.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (126)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22)));
 //BA.debugLineNum = 107;BA.debugLine="txtIngredients.Initialize(\"txtIngredients\")";
mostCurrent._txtingredients.Initialize(mostCurrent.activityBA,"txtIngredients");
 //BA.debugLineNum = 108;BA.debugLine="txtIngredients.Hint = \"List the ingredients\"";
mostCurrent._txtingredients.setHint("List the ingredients");
 //BA.debugLineNum = 109;BA.debugLine="txtIngredients.SingleLine = False";
mostCurrent._txtingredients.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 110;BA.debugLine="txtIngredients.Gravity = Gravity.TOP";
mostCurrent._txtingredients.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
 //BA.debugLineNum = 111;BA.debugLine="txtIngredients.TextSize = 15";
mostCurrent._txtingredients.setTextSize((float) (15));
 //BA.debugLineNum = 112;BA.debugLine="txtIngredients.Color = Colors.White";
mostCurrent._txtingredients.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 113;BA.debugLine="Activity.AddView(txtIngredients, 10dip, 150dip, 1";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._txtingredients.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (150)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (76)));
 //BA.debugLineNum = 115;BA.debugLine="lblInstructions.Initialize(\"\")";
mostCurrent._lblinstructions.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 116;BA.debugLine="lblInstructions.Text = \"Instructions\"";
mostCurrent._lblinstructions.setText(BA.ObjectToCharSequence("Instructions"));
 //BA.debugLineNum = 117;BA.debugLine="lblInstructions.TextSize = 14";
mostCurrent._lblinstructions.setTextSize((float) (14));
 //BA.debugLineNum = 118;BA.debugLine="lblInstructions.TextColor = Colors.RGB(55, 65, 81";
mostCurrent._lblinstructions.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (55),(int) (65),(int) (81)));
 //BA.debugLineNum = 119;BA.debugLine="Activity.AddView(lblInstructions, 10dip, 232dip,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lblinstructions.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (232)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (22)));
 //BA.debugLineNum = 121;BA.debugLine="txtInstructions.Initialize(\"txtInstructions\")";
mostCurrent._txtinstructions.Initialize(mostCurrent.activityBA,"txtInstructions");
 //BA.debugLineNum = 122;BA.debugLine="txtInstructions.Hint = \"Write the cooking steps\"";
mostCurrent._txtinstructions.setHint("Write the cooking steps");
 //BA.debugLineNum = 123;BA.debugLine="txtInstructions.SingleLine = False";
mostCurrent._txtinstructions.setSingleLine(anywheresoftware.b4a.keywords.Common.False);
 //BA.debugLineNum = 124;BA.debugLine="txtInstructions.Gravity = Gravity.TOP";
mostCurrent._txtinstructions.setGravity(anywheresoftware.b4a.keywords.Common.Gravity.TOP);
 //BA.debugLineNum = 125;BA.debugLine="txtInstructions.TextSize = 15";
mostCurrent._txtinstructions.setTextSize((float) (15));
 //BA.debugLineNum = 126;BA.debugLine="txtInstructions.Color = Colors.White";
mostCurrent._txtinstructions.setColor(anywheresoftware.b4a.keywords.Common.Colors.White);
 //BA.debugLineNum = 127;BA.debugLine="Activity.AddView(txtInstructions, 10dip, 256dip,";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._txtinstructions.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (256)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (86)));
 //BA.debugLineNum = 129;BA.debugLine="btnSave.Initialize(\"btnSave\")";
mostCurrent._btnsave.Initialize(mostCurrent.activityBA,"btnSave");
 //BA.debugLineNum = 130;BA.debugLine="btnSave.Text = \"Save\"";
mostCurrent._btnsave.setText(BA.ObjectToCharSequence("Save"));
 //BA.debugLineNum = 131;BA.debugLine="btnSave.TextSize = 15";
mostCurrent._btnsave.setTextSize((float) (15));
 //BA.debugLineNum = 132;BA.debugLine="Activity.AddView(btnSave, 10dip, 352dip, 32%x - 1";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._btnsave.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (352)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (32),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (44)));
 //BA.debugLineNum = 134;BA.debugLine="btnDelete.Initialize(\"btnDelete\")";
mostCurrent._btndelete.Initialize(mostCurrent.activityBA,"btnDelete");
 //BA.debugLineNum = 135;BA.debugLine="btnDelete.Text = \"Delete\"";
mostCurrent._btndelete.setText(BA.ObjectToCharSequence("Delete"));
 //BA.debugLineNum = 136;BA.debugLine="btnDelete.TextSize = 15";
mostCurrent._btndelete.setTextSize((float) (15));
 //BA.debugLineNum = 137;BA.debugLine="Activity.AddView(btnDelete, 34%x, 352dip, 32%x -";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._btndelete.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (34),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (352)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (32),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (44)));
 //BA.debugLineNum = 139;BA.debugLine="btnClear.Initialize(\"btnClear\")";
mostCurrent._btnclear.Initialize(mostCurrent.activityBA,"btnClear");
 //BA.debugLineNum = 140;BA.debugLine="btnClear.Text = \"Clear\"";
mostCurrent._btnclear.setText(BA.ObjectToCharSequence("Clear"));
 //BA.debugLineNum = 141;BA.debugLine="btnClear.TextSize = 15";
mostCurrent._btnclear.setTextSize((float) (15));
 //BA.debugLineNum = 142;BA.debugLine="Activity.AddView(btnClear, 67%x, 352dip, 32%x - 1";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._btnclear.getObject()),anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (67),mostCurrent.activityBA),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (352)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (32),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (12))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (44)));
 //BA.debugLineNum = 144;BA.debugLine="lblList.Initialize(\"\")";
mostCurrent._lbllist.Initialize(mostCurrent.activityBA,"");
 //BA.debugLineNum = 145;BA.debugLine="lblList.Text = \"Saved Recipes\"";
mostCurrent._lbllist.setText(BA.ObjectToCharSequence("Saved Recipes"));
 //BA.debugLineNum = 146;BA.debugLine="lblList.TextSize = 16";
mostCurrent._lbllist.setTextSize((float) (16));
 //BA.debugLineNum = 147;BA.debugLine="lblList.TextColor = Colors.RGB(31, 41, 55)";
mostCurrent._lbllist.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (31),(int) (41),(int) (55)));
 //BA.debugLineNum = 148;BA.debugLine="lblList.Typeface = Typeface.DEFAULT_BOLD";
mostCurrent._lbllist.setTypeface(anywheresoftware.b4a.keywords.Common.Typeface.DEFAULT_BOLD);
 //BA.debugLineNum = 149;BA.debugLine="Activity.AddView(lblList, 10dip, 406dip, 100%x -";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lbllist.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (406)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (24)));
 //BA.debugLineNum = 151;BA.debugLine="lvRecipes.Initialize(\"lvRecipes\")";
mostCurrent._lvrecipes.Initialize(mostCurrent.activityBA,"lvRecipes");
 //BA.debugLineNum = 152;BA.debugLine="lvRecipes.SingleLineLayout.Label.TextSize = 16";
mostCurrent._lvrecipes.getSingleLineLayout().Label.setTextSize((float) (16));
 //BA.debugLineNum = 153;BA.debugLine="lvRecipes.SingleLineLayout.Label.TextColor = Colo";
mostCurrent._lvrecipes.getSingleLineLayout().Label.setTextColor(anywheresoftware.b4a.keywords.Common.Colors.RGB((int) (17),(int) (24),(int) (39)));
 //BA.debugLineNum = 154;BA.debugLine="Activity.AddView(lvRecipes, 10dip, 434dip, 100%x";
mostCurrent._activity.AddView((android.view.View)(mostCurrent._lvrecipes.getObject()),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (10)),anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (434)),(int) (anywheresoftware.b4a.keywords.Common.PerXToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (20))),(int) (anywheresoftware.b4a.keywords.Common.PerYToCurrent((float) (100),mostCurrent.activityBA)-anywheresoftware.b4a.keywords.Common.DipToCurrent((int) (444))));
 //BA.debugLineNum = 155;BA.debugLine="End Sub";
return "";
}
public static String  _clearform() throws Exception{
 //BA.debugLineNum = 174;BA.debugLine="Private Sub ClearForm";
 //BA.debugLineNum = 175;BA.debugLine="SelectedRecipeId = -1";
_selectedrecipeid = (int) (-1);
 //BA.debugLineNum = 176;BA.debugLine="txtName.Text = \"\"";
mostCurrent._txtname.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 177;BA.debugLine="txtIngredients.Text = \"\"";
mostCurrent._txtingredients.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 178;BA.debugLine="txtInstructions.Text = \"\"";
mostCurrent._txtinstructions.setText(BA.ObjectToCharSequence(""));
 //BA.debugLineNum = 179;BA.debugLine="btnSave.Text = \"Save\"";
mostCurrent._btnsave.setText(BA.ObjectToCharSequence("Save"));
 //BA.debugLineNum = 180;BA.debugLine="End Sub";
return "";
}
public static String  _globals() throws Exception{
 //BA.debugLineNum = 20;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 21;BA.debugLine="Private lblHeader As Label";
mostCurrent._lblheader = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 22;BA.debugLine="Private lblName As Label";
mostCurrent._lblname = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 23;BA.debugLine="Private lblIngredients As Label";
mostCurrent._lblingredients = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 24;BA.debugLine="Private lblInstructions As Label";
mostCurrent._lblinstructions = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 25;BA.debugLine="Private lblList As Label";
mostCurrent._lbllist = new anywheresoftware.b4a.objects.LabelWrapper();
 //BA.debugLineNum = 26;BA.debugLine="Private txtName As EditText";
mostCurrent._txtname = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 27;BA.debugLine="Private txtIngredients As EditText";
mostCurrent._txtingredients = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 28;BA.debugLine="Private txtInstructions As EditText";
mostCurrent._txtinstructions = new anywheresoftware.b4a.objects.EditTextWrapper();
 //BA.debugLineNum = 29;BA.debugLine="Private btnSave As Button";
mostCurrent._btnsave = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 30;BA.debugLine="Private btnDelete As Button";
mostCurrent._btndelete = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 31;BA.debugLine="Private btnClear As Button";
mostCurrent._btnclear = new anywheresoftware.b4a.objects.ButtonWrapper();
 //BA.debugLineNum = 32;BA.debugLine="Private lvRecipes As ListView";
mostCurrent._lvrecipes = new anywheresoftware.b4a.objects.ListViewWrapper();
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return "";
}
public static String  _loadrecipes() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rs = null;
int _i = 0;
 //BA.debugLineNum = 157;BA.debugLine="Private Sub LoadRecipes";
 //BA.debugLineNum = 158;BA.debugLine="If SQL1.IsInitialized = False Then Return";
if (_sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 159;BA.debugLine="If lvRecipes.IsInitialized = False Then Return";
if (mostCurrent._lvrecipes.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
if (true) return "";};
 //BA.debugLineNum = 161;BA.debugLine="lvRecipes.Clear";
mostCurrent._lvrecipes.Clear();
 //BA.debugLineNum = 162;BA.debugLine="Dim rs As Cursor = SQL1.ExecQuery(\"SELECT id, nam";
_rs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql1.ExecQuery("SELECT id, name FROM recipes ORDER BY name COLLATE NOCASE")));
 //BA.debugLineNum = 163;BA.debugLine="For i = 0 To rs.RowCount - 1";
{
final int step5 = 1;
final int limit5 = (int) (_rs.getRowCount()-1);
_i = (int) (0) ;
for (;_i <= limit5 ;_i = _i + step5 ) {
 //BA.debugLineNum = 164;BA.debugLine="rs.Position = i";
_rs.setPosition(_i);
 //BA.debugLineNum = 165;BA.debugLine="lvRecipes.AddSingleLine2(rs.GetString(\"name\"), r";
mostCurrent._lvrecipes.AddSingleLine2(BA.ObjectToCharSequence(_rs.GetString("name")),(Object)(_rs.GetInt("id")));
 }
};
 //BA.debugLineNum = 167;BA.debugLine="rs.Close";
_rs.Close();
 //BA.debugLineNum = 169;BA.debugLine="If lvRecipes.Size = 0 Then";
if (mostCurrent._lvrecipes.getSize()==0) { 
 //BA.debugLineNum = 170;BA.debugLine="lvRecipes.AddSingleLine2(\"No recipes yet. Add on";
mostCurrent._lvrecipes.AddSingleLine2(BA.ObjectToCharSequence("No recipes yet. Add one above."),(Object)(-1));
 };
 //BA.debugLineNum = 172;BA.debugLine="End Sub";
return "";
}
public static String  _lvrecipes_itemclick(int _position,Object _value) throws Exception{
int _recipeid = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _rs = null;
 //BA.debugLineNum = 219;BA.debugLine="Sub lvRecipes_ItemClick (Position As Int, Value As";
 //BA.debugLineNum = 220;BA.debugLine="Dim recipeId As Int = Value";
_recipeid = (int)(BA.ObjectToNumber(_value));
 //BA.debugLineNum = 221;BA.debugLine="If recipeId = -1 Then Return";
if (_recipeid==-1) { 
if (true) return "";};
 //BA.debugLineNum = 223;BA.debugLine="SelectedRecipeId = recipeId";
_selectedrecipeid = _recipeid;
 //BA.debugLineNum = 224;BA.debugLine="Dim rs As Cursor = SQL1.ExecQuery2(\"SELECT name,";
_rs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql1.ExecQuery2("SELECT name, ingredients, instructions FROM recipes WHERE id = ?",new String[]{BA.NumberToString(_recipeid)})));
 //BA.debugLineNum = 225;BA.debugLine="If rs.RowCount > 0 Then";
if (_rs.getRowCount()>0) { 
 //BA.debugLineNum = 226;BA.debugLine="rs.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 227;BA.debugLine="txtName.Text = rs.GetString(\"name\")";
mostCurrent._txtname.setText(BA.ObjectToCharSequence(_rs.GetString("name")));
 //BA.debugLineNum = 228;BA.debugLine="txtIngredients.Text = rs.GetString(\"ingredients\"";
mostCurrent._txtingredients.setText(BA.ObjectToCharSequence(_rs.GetString("ingredients")));
 //BA.debugLineNum = 229;BA.debugLine="txtInstructions.Text = rs.GetString(\"instruction";
mostCurrent._txtinstructions.setText(BA.ObjectToCharSequence(_rs.GetString("instructions")));
 //BA.debugLineNum = 230;BA.debugLine="btnSave.Text = \"Update\"";
mostCurrent._btnsave.setText(BA.ObjectToCharSequence("Update"));
 };
 //BA.debugLineNum = 232;BA.debugLine="rs.Close";
_rs.Close();
 //BA.debugLineNum = 233;BA.debugLine="End Sub";
return "";
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main._process_globals();
starter._process_globals();
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static String  _process_globals() throws Exception{
 //BA.debugLineNum = 14;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 15;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
 //BA.debugLineNum = 16;BA.debugLine="Private SQL1 As SQL";
_sql1 = new anywheresoftware.b4a.sql.SQL();
 //BA.debugLineNum = 17;BA.debugLine="Private SelectedRecipeId As Int = -1";
_selectedrecipeid = (int) (-1);
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return "";
}
public static String  _seedrecipesifempty() throws Exception{
anywheresoftware.b4a.sql.SQL.CursorWrapper _rs = null;
 //BA.debugLineNum = 63;BA.debugLine="Private Sub SeedRecipesIfEmpty";
 //BA.debugLineNum = 64;BA.debugLine="Dim rs As Cursor = SQL1.ExecQuery(\"SELECT COUNT(*";
_rs = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
_rs = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql1.ExecQuery("SELECT COUNT(*) AS total FROM recipes")));
 //BA.debugLineNum = 65;BA.debugLine="rs.Position = 0";
_rs.setPosition((int) (0));
 //BA.debugLineNum = 66;BA.debugLine="If rs.GetInt(\"total\") = 0 Then";
if (_rs.GetInt("total")==0) { 
 //BA.debugLineNum = 67;BA.debugLine="SQL1.ExecNonQuery2(\"INSERT INTO recipes (name, i";
_sql1.ExecNonQuery2("INSERT INTO recipes (name, ingredients, instructions) VALUES (?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)("Chicken Adobo"),(Object)("Chicken, soy sauce, vinegar, garlic, bay leaf, pepper"),(Object)("Marinate chicken. Simmer with soy sauce, vinegar, garlic, bay leaf, and pepper until tender.")}));
 //BA.debugLineNum = 69;BA.debugLine="SQL1.ExecNonQuery2(\"INSERT INTO recipes (name, i";
_sql1.ExecNonQuery2("INSERT INTO recipes (name, ingredients, instructions) VALUES (?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)("Garlic Fried Rice"),(Object)("Cooked rice, garlic, oil, salt"),(Object)("Toast garlic in oil. Add rice and salt. Stir fry until hot.")}));
 //BA.debugLineNum = 71;BA.debugLine="SQL1.ExecNonQuery2(\"INSERT INTO recipes (name, i";
_sql1.ExecNonQuery2("INSERT INTO recipes (name, ingredients, instructions) VALUES (?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)("Mango Float"),(Object)("Graham crackers, cream, condensed milk, mangoes"),(Object)("Layer crackers, sweet cream, and mango slices. Chill before serving.")}));
 };
 //BA.debugLineNum = 74;BA.debugLine="rs.Close";
_rs.Close();
 //BA.debugLineNum = 75;BA.debugLine="End Sub";
return "";
}
public static String  _setupdatabase() throws Exception{
 //BA.debugLineNum = 55;BA.debugLine="Private Sub SetupDatabase";
 //BA.debugLineNum = 56;BA.debugLine="If SQL1.IsInitialized = False Then";
if (_sql1.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
 //BA.debugLineNum = 57;BA.debugLine="SQL1.Initialize(File.DirInternal, \"recipes.db\",";
_sql1.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"recipes.db",anywheresoftware.b4a.keywords.Common.True);
 };
 //BA.debugLineNum = 59;BA.debugLine="SQL1.ExecNonQuery(\"CREATE TABLE IF NOT EXISTS rec";
_sql1.ExecNonQuery("CREATE TABLE IF NOT EXISTS recipes (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, ingredients TEXT, instructions TEXT)");
 //BA.debugLineNum = 60;BA.debugLine="SeedRecipesIfEmpty";
_seedrecipesifempty();
 //BA.debugLineNum = 61;BA.debugLine="End Sub";
return "";
}
}
