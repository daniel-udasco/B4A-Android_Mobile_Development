package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,36);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 36;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(8);
 BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"main\")";
Debug.ShouldStop(16);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("main")),main.mostCurrent.activityBA);
 BA.debugLineNum = 39;BA.debugLine="Drawer.Initialize(Me, \"Drawer\", Activity, 260dip)";
Debug.ShouldStop(64);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_initialize" /*RemoteObject*/ ,main.mostCurrent.activityBA,(Object)(main.getObject()),(Object)(BA.ObjectToString("Drawer")),RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.B4XViewWrapper"), main.mostCurrent._activity.getObject()),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 260)))));
 BA.debugLineNum = 40;BA.debugLine="Drawer.CenterPanel.BringToFront";
Debug.ShouldStop(128);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 41;BA.debugLine="Drawer.LeftPanel.BringToFront";
Debug.ShouldStop(256);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).runVoidMethod ("BringToFront");
 BA.debugLineNum = 43;BA.debugLine="pnlMain = Drawer.CenterPanel";
Debug.ShouldStop(1024);
main.mostCurrent._pnlmain = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getcenterpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 44;BA.debugLine="pnlMenu = Drawer.LeftPanel";
Debug.ShouldStop(2048);
main.mostCurrent._pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.PanelWrapper"), main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftpanel" /*RemoteObject*/ ).getObject());
 BA.debugLineNum = 46;BA.debugLine="pnlMenu.Color = Colors.RGB(33,150,243)";
Debug.ShouldStop(8192);
main.mostCurrent._pnlmenu.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").runMethod(true,"RGB",(Object)(BA.numberCast(int.class, 33)),(Object)(BA.numberCast(int.class, 150)),(Object)(BA.numberCast(int.class, 243))));
 BA.debugLineNum = 48;BA.debugLine="CreateMenu";
Debug.ShouldStop(32768);
_createmenu();
 BA.debugLineNum = 49;BA.debugLine="ShowHome";
Debug.ShouldStop(65536);
_showhome();
 BA.debugLineNum = 50;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_keypress(RemoteObject _keycode) throws Exception{
try {
		Debug.PushSubsStack("Activity_KeyPress (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,178);
if (RapidSub.canDelegate("activity_keypress")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_keypress", _keycode);}
Debug.locals.put("KeyCode", _keycode);
 BA.debugLineNum = 178;BA.debugLine="Sub Activity_KeyPress (KeyCode As Int) As Boolean";
Debug.ShouldStop(131072);
 BA.debugLineNum = 179;BA.debugLine="If KeyCode = KeyCodes.KEYCODE_BACK And Drawer.Lef";
Debug.ShouldStop(262144);
if (RemoteObject.solveBoolean("=",_keycode,BA.numberCast(double.class, main.mostCurrent.__c.getField(false,"KeyCodes").getField(true,"KEYCODE_BACK"))) && RemoteObject.solveBoolean(".",main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))) { 
 BA.debugLineNum = 180;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(524288);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 181;BA.debugLine="Return True";
Debug.ShouldStop(1048576);
if (true) return main.mostCurrent.__c.getField(true,"True");
 };
 BA.debugLineNum = 183;BA.debugLine="Return False";
Debug.ShouldStop(4194304);
if (true) return main.mostCurrent.__c.getField(true,"False");
 BA.debugLineNum = 184;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable(false);
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,56);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 56;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 58;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,52);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 52;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(524288);
 BA.debugLineNum = 54;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnhome_click() throws Exception{
try {
		Debug.PushSubsStack("btnHome_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,99);
if (RapidSub.canDelegate("btnhome_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnhome_click");}
 BA.debugLineNum = 99;BA.debugLine="Sub btnHome_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 100;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(8);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 101;BA.debugLine="ShowHome";
Debug.ShouldStop(16);
_showhome();
 BA.debugLineNum = 102;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnmenu_click() throws Exception{
try {
		Debug.PushSubsStack("btnMenu_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,60);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnmenu_click");}
 BA.debugLineNum = 60;BA.debugLine="Sub btnMenu_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 61;BA.debugLine="Drawer.LeftOpen = Not(Drawer.LeftOpen)";
Debug.ShouldStop(268435456);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.runMethod(true,"Not",(Object)(main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_getleftopen" /*RemoteObject*/ ))));
 BA.debugLineNum = 62;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage1_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,104);
if (RapidSub.canDelegate("btnpage1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage1_click");}
 BA.debugLineNum = 104;BA.debugLine="Sub btnPage1_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 105;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(256);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 106;BA.debugLine="ShowPage1";
Debug.ShouldStop(512);
_showpage1();
 BA.debugLineNum = 107;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage2_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,109);
if (RapidSub.canDelegate("btnpage2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage2_click");}
 BA.debugLineNum = 109;BA.debugLine="Sub btnPage2_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 110;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(8192);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 111;BA.debugLine="ShowPage2";
Debug.ShouldStop(16384);
_showpage2();
 BA.debugLineNum = 112;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage3_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,114);
if (RapidSub.canDelegate("btnpage3_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage3_click");}
 BA.debugLineNum = 114;BA.debugLine="Sub btnPage3_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 115;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(262144);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 116;BA.debugLine="ShowPage3";
Debug.ShouldStop(524288);
_showpage3();
 BA.debugLineNum = 117;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage4_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage4_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,119);
if (RapidSub.canDelegate("btnpage4_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage4_click");}
 BA.debugLineNum = 119;BA.debugLine="Sub btnPage4_Click";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 120;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(8388608);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 121;BA.debugLine="ShowPage4";
Debug.ShouldStop(16777216);
_showpage4();
 BA.debugLineNum = 122;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage5_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage5_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,124);
if (RapidSub.canDelegate("btnpage5_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage5_click");}
 BA.debugLineNum = 124;BA.debugLine="Sub btnPage5_Click";
Debug.ShouldStop(134217728);
 BA.debugLineNum = 125;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(268435456);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 126;BA.debugLine="ShowPage5";
Debug.ShouldStop(536870912);
_showpage5();
 BA.debugLineNum = 127;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnpage6_click() throws Exception{
try {
		Debug.PushSubsStack("btnPage6_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,129);
if (RapidSub.canDelegate("btnpage6_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnpage6_click");}
 BA.debugLineNum = 129;BA.debugLine="Sub btnPage6_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 130;BA.debugLine="Drawer.LeftOpen = False";
Debug.ShouldStop(2);
main.mostCurrent._drawer.runClassMethod (b4a.example.b4xdrawer.class, "_setleftopen" /*RemoteObject*/ ,main.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 131;BA.debugLine="ShowPage6";
Debug.ShouldStop(4);
_showpage6();
 BA.debugLineNum = 132;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _button1_click() throws Exception{
try {
		Debug.PushSubsStack("Button1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,210);
if (RapidSub.canDelegate("button1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","button1_click");}
RemoteObject _ans = RemoteObject.createImmutable(0);
 BA.debugLineNum = 210;BA.debugLine="Private Sub Button1_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 212;BA.debugLine="If EditText1.Text = \"\" Then";
Debug.ShouldStop(524288);
if (RemoteObject.solveBoolean("=",main.mostCurrent._edittext1.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 213;BA.debugLine="MsgboxAsync(\"Please enter a value\", \"Input Error";
Debug.ShouldStop(1048576);
main.mostCurrent.__c.runVoidMethod ("MsgboxAsync",(Object)(BA.ObjectToCharSequence("Please enter a value")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable("Input Error"))),main.processBA);
 BA.debugLineNum = 214;BA.debugLine="Return";
Debug.ShouldStop(2097152);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 217;BA.debugLine="Dim Ans As Double";
Debug.ShouldStop(16777216);
_ans = RemoteObject.createImmutable(0);Debug.locals.put("Ans", _ans);
 BA.debugLineNum = 218;BA.debugLine="Dim units As String ' Ensure this is declared if";
Debug.ShouldStop(33554432);
main.mostCurrent._units = RemoteObject.createImmutable("");
 BA.debugLineNum = 220;BA.debugLine="Select lblTitle.Text";
Debug.ShouldStop(134217728);
switch (BA.switchObjectToInt(main.mostCurrent._lbltitle.runMethod(true,"getText"),BA.ObjectToString("Inches To Centimeter"),BA.ObjectToString("Centimeter To Inches"),BA.ObjectToString("Inches To Feet"),BA.ObjectToString("Feet To Inches"),BA.ObjectToString("Centimeter To Meter"),BA.ObjectToString("Meter To Centimeter"))) {
case 0: {
 BA.debugLineNum = 222;BA.debugLine="Ans = (EditText1.Text) * 2.54";
Debug.ShouldStop(536870912);
_ans = RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, (main.mostCurrent._edittext1.runMethod(true,"getText"))),RemoteObject.createImmutable(2.54)}, "*",0, 0);Debug.locals.put("Ans", _ans);
 BA.debugLineNum = 223;BA.debugLine="units = \" cm\"";
Debug.ShouldStop(1073741824);
main.mostCurrent._units = BA.ObjectToString(" cm");
 break; }
case 1: {
 BA.debugLineNum = 226;BA.debugLine="Ans = (EditText1.Text) / 2.54";
Debug.ShouldStop(2);
_ans = RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, (main.mostCurrent._edittext1.runMethod(true,"getText"))),RemoteObject.createImmutable(2.54)}, "/",0, 0);Debug.locals.put("Ans", _ans);
 BA.debugLineNum = 227;BA.debugLine="units = \" in\"";
Debug.ShouldStop(4);
main.mostCurrent._units = BA.ObjectToString(" in");
 break; }
case 2: {
 BA.debugLineNum = 230;BA.debugLine="Ans = (EditText1.Text) / 12";
Debug.ShouldStop(32);
_ans = RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, (main.mostCurrent._edittext1.runMethod(true,"getText"))),RemoteObject.createImmutable(12)}, "/",0, 0);Debug.locals.put("Ans", _ans);
 BA.debugLineNum = 231;BA.debugLine="units = \" ft\"";
Debug.ShouldStop(64);
main.mostCurrent._units = BA.ObjectToString(" ft");
 break; }
case 3: {
 BA.debugLineNum = 234;BA.debugLine="Ans = (EditText1.Text) * 12";
Debug.ShouldStop(512);
_ans = RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, (main.mostCurrent._edittext1.runMethod(true,"getText"))),RemoteObject.createImmutable(12)}, "*",0, 0);Debug.locals.put("Ans", _ans);
 BA.debugLineNum = 235;BA.debugLine="units = \" in\"";
Debug.ShouldStop(1024);
main.mostCurrent._units = BA.ObjectToString(" in");
 break; }
case 4: {
 BA.debugLineNum = 238;BA.debugLine="Ans = (EditText1.Text) / 100";
Debug.ShouldStop(8192);
_ans = RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, (main.mostCurrent._edittext1.runMethod(true,"getText"))),RemoteObject.createImmutable(100)}, "/",0, 0);Debug.locals.put("Ans", _ans);
 BA.debugLineNum = 239;BA.debugLine="units = \" m\"";
Debug.ShouldStop(16384);
main.mostCurrent._units = BA.ObjectToString(" m");
 break; }
case 5: {
 BA.debugLineNum = 242;BA.debugLine="Ans = (EditText1.Text) * 100";
Debug.ShouldStop(131072);
_ans = RemoteObject.solve(new RemoteObject[] {BA.numberCast(double.class, (main.mostCurrent._edittext1.runMethod(true,"getText"))),RemoteObject.createImmutable(100)}, "*",0, 0);Debug.locals.put("Ans", _ans);
 BA.debugLineNum = 243;BA.debugLine="units = \" cm\"";
Debug.ShouldStop(262144);
main.mostCurrent._units = BA.ObjectToString(" cm");
 break; }
default: {
 BA.debugLineNum = 246;BA.debugLine="Ans = 0";
Debug.ShouldStop(2097152);
_ans = BA.numberCast(double.class, 0);Debug.locals.put("Ans", _ans);
 BA.debugLineNum = 247;BA.debugLine="units = \"\"";
Debug.ShouldStop(4194304);
main.mostCurrent._units = BA.ObjectToString("");
 break; }
}
;
 BA.debugLineNum = 252;BA.debugLine="Label1.Text = NumberFormat(Ans, 1, 2) & units";
Debug.ShouldStop(134217728);
main.mostCurrent._label1.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(main.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_ans),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2))),main.mostCurrent._units)));
 BA.debugLineNum = 253;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _createmenu() throws Exception{
try {
		Debug.PushSubsStack("CreateMenu (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,64);
if (RapidSub.canDelegate("createmenu")) { return b4a.example.main.remoteMe.runUserSub(false, "main","createmenu");}
RemoteObject _b = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 64;BA.debugLine="Sub CreateMenu";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 65;BA.debugLine="Dim btnHome, btnPage1, btnPage2 As Button";
Debug.ShouldStop(1);
main.mostCurrent._btnhome = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 BA.debugLineNum = 66;BA.debugLine="btnHome.Initialize(\"btnHome\")";
Debug.ShouldStop(2);
main.mostCurrent._btnhome.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnHome")));
 BA.debugLineNum = 67;BA.debugLine="btnHome.Text = \"Home\"";
Debug.ShouldStop(4);
main.mostCurrent._btnhome.runMethod(true,"setText",BA.ObjectToCharSequence("Home"));
 BA.debugLineNum = 68;BA.debugLine="btnPage1.Initialize(\"btnPage1\")";
Debug.ShouldStop(8);
main.mostCurrent._btnpage1.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage1")));
 BA.debugLineNum = 69;BA.debugLine="btnPage1.Text = \"Inches To Centimeter\"";
Debug.ShouldStop(16);
main.mostCurrent._btnpage1.runMethod(true,"setText",BA.ObjectToCharSequence("Inches To Centimeter"));
 BA.debugLineNum = 70;BA.debugLine="btnPage2.Initialize(\"btnPage2\")";
Debug.ShouldStop(32);
main.mostCurrent._btnpage2.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage2")));
 BA.debugLineNum = 71;BA.debugLine="btnPage2.Text = \"Centimeter To Inches\"";
Debug.ShouldStop(64);
main.mostCurrent._btnpage2.runMethod(true,"setText",BA.ObjectToCharSequence("Centimeter To Inches"));
 BA.debugLineNum = 72;BA.debugLine="btnPage3.Initialize(\"btnPage3\")";
Debug.ShouldStop(128);
main.mostCurrent._btnpage3.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage3")));
 BA.debugLineNum = 73;BA.debugLine="btnPage3.Text = \"Inches To Feet\"";
Debug.ShouldStop(256);
main.mostCurrent._btnpage3.runMethod(true,"setText",BA.ObjectToCharSequence("Inches To Feet"));
 BA.debugLineNum = 74;BA.debugLine="btnPage4.Initialize(\"btnPage4\")";
Debug.ShouldStop(512);
main.mostCurrent._btnpage4.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage4")));
 BA.debugLineNum = 75;BA.debugLine="btnPage4.Text = \"Feet To Inches\"";
Debug.ShouldStop(1024);
main.mostCurrent._btnpage4.runMethod(true,"setText",BA.ObjectToCharSequence("Feet To Inches"));
 BA.debugLineNum = 76;BA.debugLine="btnPage5.Initialize(\"btnPage5\")";
Debug.ShouldStop(2048);
main.mostCurrent._btnpage5.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage5")));
 BA.debugLineNum = 77;BA.debugLine="btnPage5.Text = \"Centimeter To Meter\"";
Debug.ShouldStop(4096);
main.mostCurrent._btnpage5.runMethod(true,"setText",BA.ObjectToCharSequence("Centimeter To Meter"));
 BA.debugLineNum = 78;BA.debugLine="btnPage6.Initialize(\"btnPage6\")";
Debug.ShouldStop(8192);
main.mostCurrent._btnpage6.runVoidMethod ("Initialize",main.mostCurrent.activityBA,(Object)(RemoteObject.createImmutable("btnPage6")));
 BA.debugLineNum = 79;BA.debugLine="btnPage6.Text = \"Meter To Centimeter\"";
Debug.ShouldStop(16384);
main.mostCurrent._btnpage6.runMethod(true,"setText",BA.ObjectToCharSequence("Meter To Centimeter"));
 BA.debugLineNum = 81;BA.debugLine="For Each b As Button In Array(btnHome, btnPage1,";
Debug.ShouldStop(65536);
_b = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
{
final RemoteObject group16 = RemoteObject.createNewArray("Object",new int[] {7},new Object[] {(main.mostCurrent._btnhome.getObject()),(main.mostCurrent._btnpage1.getObject()),(main.mostCurrent._btnpage2.getObject()),(main.mostCurrent._btnpage3.getObject()),(main.mostCurrent._btnpage4.getObject()),(main.mostCurrent._btnpage5.getObject()),(main.mostCurrent._btnpage6.getObject())});
final int groupLen16 = group16.getField(true,"length").<Integer>get()
;int index16 = 0;
;
for (; index16 < groupLen16;index16++){
_b = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.objects.ButtonWrapper"), group16.getArrayElement(false,RemoteObject.createImmutable(index16)));Debug.locals.put("b", _b);
Debug.locals.put("b", _b);
 BA.debugLineNum = 82;BA.debugLine="b.TextSize = 16";
Debug.ShouldStop(131072);
_b.runMethod(true,"setTextSize",BA.numberCast(float.class, 16));
 BA.debugLineNum = 83;BA.debugLine="b.Gravity = Gravity.LEFT + Gravity.CENTER_VERTIC";
Debug.ShouldStop(262144);
_b.runMethod(true,"setGravity",RemoteObject.solve(new RemoteObject[] {main.mostCurrent.__c.getField(false,"Gravity").getField(true,"LEFT"),main.mostCurrent.__c.getField(false,"Gravity").getField(true,"CENTER_VERTICAL")}, "+",1, 1));
 BA.debugLineNum = 84;BA.debugLine="b.Color = Colors.Transparent";
Debug.ShouldStop(524288);
_b.runVoidMethod ("setColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"Transparent"));
 BA.debugLineNum = 85;BA.debugLine="b.TextColor = Colors.White";
Debug.ShouldStop(1048576);
_b.runMethod(true,"setTextColor",main.mostCurrent.__c.getField(false,"Colors").getField(true,"White"));
 BA.debugLineNum = 86;BA.debugLine="pnlMenu.AddView(b, 10dip, 0, 240dip, 50dip)";
Debug.ShouldStop(2097152);
main.mostCurrent._pnlmenu.runVoidMethod ("AddView",(Object)((_b.getObject())),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 10)))),(Object)(BA.numberCast(int.class, 0)),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240)))),(Object)(main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 50)))));
 }
}Debug.locals.put("b", _b);
;
 BA.debugLineNum = 88;BA.debugLine="btnHome.Top = 120dip";
Debug.ShouldStop(8388608);
main.mostCurrent._btnhome.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 120))));
 BA.debugLineNum = 89;BA.debugLine="btnPage1.Top = 180dip";
Debug.ShouldStop(16777216);
main.mostCurrent._btnpage1.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 180))));
 BA.debugLineNum = 90;BA.debugLine="btnPage2.Top = 240dip";
Debug.ShouldStop(33554432);
main.mostCurrent._btnpage2.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 240))));
 BA.debugLineNum = 91;BA.debugLine="btnPage3.Top = 300dip";
Debug.ShouldStop(67108864);
main.mostCurrent._btnpage3.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 300))));
 BA.debugLineNum = 92;BA.debugLine="btnPage4.Top = 360dip";
Debug.ShouldStop(134217728);
main.mostCurrent._btnpage4.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 360))));
 BA.debugLineNum = 93;BA.debugLine="btnPage5.Top = 420dip";
Debug.ShouldStop(268435456);
main.mostCurrent._btnpage5.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 420))));
 BA.debugLineNum = 94;BA.debugLine="btnPage6.Top = 480dip";
Debug.ShouldStop(536870912);
main.mostCurrent._btnpage6.runMethod(true,"setTop",main.mostCurrent.__c.runMethod(true,"DipToCurrent",(Object)(BA.numberCast(int.class, 480))));
 BA.debugLineNum = 95;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 21;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 23;BA.debugLine="Private Drawer As B4XDrawer";
main.mostCurrent._drawer = RemoteObject.createNew ("b4a.example.b4xdrawer");
 //BA.debugLineNum = 24;BA.debugLine="Private pnlMain As Panel";
main.mostCurrent._pnlmain = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private pnlMenu As Panel";
main.mostCurrent._pnlmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 28;BA.debugLine="Private lblTitle As Label";
main.mostCurrent._lbltitle = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 29;BA.debugLine="Private btnHome, btnPage1, btnPage2, btnPage3, bt";
main.mostCurrent._btnhome = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage2 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage3 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage4 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage5 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
main.mostCurrent._btnpage6 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 31;BA.debugLine="Private EditText1 As EditText";
main.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 32;BA.debugLine="Private Label1 As Label";
main.mostCurrent._label1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 33;BA.debugLine="Private units As String";
main.mostCurrent._units = RemoteObject.createImmutable("");
 //BA.debugLineNum = 34;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _pnl1_click() throws Exception{
try {
		Debug.PushSubsStack("pnl1_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,206);
if (RapidSub.canDelegate("pnl1_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl1_click");}
 BA.debugLineNum = 206;BA.debugLine="Private Sub pnl1_Click";
Debug.ShouldStop(8192);
 BA.debugLineNum = 207;BA.debugLine="ShowPage1";
Debug.ShouldStop(16384);
_showpage1();
 BA.debugLineNum = 208;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl2_click() throws Exception{
try {
		Debug.PushSubsStack("pnl2_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,202);
if (RapidSub.canDelegate("pnl2_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl2_click");}
 BA.debugLineNum = 202;BA.debugLine="Private Sub pnl2_Click";
Debug.ShouldStop(512);
 BA.debugLineNum = 203;BA.debugLine="ShowPage2";
Debug.ShouldStop(1024);
_showpage2();
 BA.debugLineNum = 204;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl3_click() throws Exception{
try {
		Debug.PushSubsStack("pnl3_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,198);
if (RapidSub.canDelegate("pnl3_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl3_click");}
 BA.debugLineNum = 198;BA.debugLine="Private Sub pnl3_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 199;BA.debugLine="ShowPage3";
Debug.ShouldStop(64);
_showpage3();
 BA.debugLineNum = 200;BA.debugLine="End Sub";
Debug.ShouldStop(128);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl4_click() throws Exception{
try {
		Debug.PushSubsStack("pnl4_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,194);
if (RapidSub.canDelegate("pnl4_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl4_click");}
 BA.debugLineNum = 194;BA.debugLine="Private Sub pnl4_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 195;BA.debugLine="ShowPage4";
Debug.ShouldStop(4);
_showpage4();
 BA.debugLineNum = 196;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl5_click() throws Exception{
try {
		Debug.PushSubsStack("pnl5_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,190);
if (RapidSub.canDelegate("pnl5_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl5_click");}
 BA.debugLineNum = 190;BA.debugLine="Private Sub pnl5_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 191;BA.debugLine="ShowPage5";
Debug.ShouldStop(1073741824);
_showpage5();
 BA.debugLineNum = 192;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _pnl6_click() throws Exception{
try {
		Debug.PushSubsStack("pnl6_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,186);
if (RapidSub.canDelegate("pnl6_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","pnl6_click");}
 BA.debugLineNum = 186;BA.debugLine="Private Sub pnl6_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 187;BA.debugLine="ShowPage6";
Debug.ShouldStop(67108864);
_showpage6();
 BA.debugLineNum = 188;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
starter_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
b4xdrawer.myClass = BA.getDeviceClass ("b4a.example.b4xdrawer");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 18;BA.debugLine="Private xui As XUI";
main._xui = RemoteObject.createNew ("anywheresoftware.b4a.objects.B4XViewWrapper.XUI");
 //BA.debugLineNum = 19;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showhome() throws Exception{
try {
		Debug.PushSubsStack("ShowHome (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,136);
if (RapidSub.canDelegate("showhome")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showhome");}
 BA.debugLineNum = 136;BA.debugLine="Sub ShowHome";
Debug.ShouldStop(128);
 BA.debugLineNum = 137;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(256);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 138;BA.debugLine="pnlMain.LoadLayout(\"home\")";
Debug.ShouldStop(512);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("home")),main.mostCurrent.activityBA);
 BA.debugLineNum = 139;BA.debugLine="lblTitle.Text = \"Home\"";
Debug.ShouldStop(1024);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Home"));
 BA.debugLineNum = 140;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage1() throws Exception{
try {
		Debug.PushSubsStack("ShowPage1 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,142);
if (RapidSub.canDelegate("showpage1")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage1");}
 BA.debugLineNum = 142;BA.debugLine="Sub ShowPage1";
Debug.ShouldStop(8192);
 BA.debugLineNum = 143;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(16384);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 144;BA.debugLine="pnlMain.LoadLayout(\"page1\")";
Debug.ShouldStop(32768);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page1")),main.mostCurrent.activityBA);
 BA.debugLineNum = 145;BA.debugLine="lblTitle.Text = \"Inches To Centimeter\"";
Debug.ShouldStop(65536);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Inches To Centimeter"));
 BA.debugLineNum = 146;BA.debugLine="End Sub";
Debug.ShouldStop(131072);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage2() throws Exception{
try {
		Debug.PushSubsStack("ShowPage2 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,148);
if (RapidSub.canDelegate("showpage2")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage2");}
 BA.debugLineNum = 148;BA.debugLine="Sub ShowPage2";
Debug.ShouldStop(524288);
 BA.debugLineNum = 149;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(1048576);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 150;BA.debugLine="pnlMain.LoadLayout(\"page2\")";
Debug.ShouldStop(2097152);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page2")),main.mostCurrent.activityBA);
 BA.debugLineNum = 151;BA.debugLine="lblTitle.Text = \"Centimeter To Inches\"";
Debug.ShouldStop(4194304);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Centimeter To Inches"));
 BA.debugLineNum = 152;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage3() throws Exception{
try {
		Debug.PushSubsStack("ShowPage3 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,154);
if (RapidSub.canDelegate("showpage3")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage3");}
 BA.debugLineNum = 154;BA.debugLine="Sub ShowPage3";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 155;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(67108864);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 156;BA.debugLine="pnlMain.LoadLayout(\"page3\")";
Debug.ShouldStop(134217728);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page3")),main.mostCurrent.activityBA);
 BA.debugLineNum = 157;BA.debugLine="lblTitle.Text = \"Inches To Feet\"";
Debug.ShouldStop(268435456);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Inches To Feet"));
 BA.debugLineNum = 158;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage4() throws Exception{
try {
		Debug.PushSubsStack("ShowPage4 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,160);
if (RapidSub.canDelegate("showpage4")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage4");}
 BA.debugLineNum = 160;BA.debugLine="Sub ShowPage4";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 161;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(1);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 162;BA.debugLine="pnlMain.LoadLayout(\"page4\")";
Debug.ShouldStop(2);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page4")),main.mostCurrent.activityBA);
 BA.debugLineNum = 163;BA.debugLine="lblTitle.Text = \"Feet To Inches\"";
Debug.ShouldStop(4);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Feet To Inches"));
 BA.debugLineNum = 164;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage5() throws Exception{
try {
		Debug.PushSubsStack("ShowPage5 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,166);
if (RapidSub.canDelegate("showpage5")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage5");}
 BA.debugLineNum = 166;BA.debugLine="Sub ShowPage5";
Debug.ShouldStop(32);
 BA.debugLineNum = 167;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(64);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 168;BA.debugLine="pnlMain.LoadLayout(\"page5\")";
Debug.ShouldStop(128);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page5")),main.mostCurrent.activityBA);
 BA.debugLineNum = 169;BA.debugLine="lblTitle.Text = \"Centimeter To Meter\"";
Debug.ShouldStop(256);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Centimeter To Meter"));
 BA.debugLineNum = 170;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _showpage6() throws Exception{
try {
		Debug.PushSubsStack("ShowPage6 (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,172);
if (RapidSub.canDelegate("showpage6")) { return b4a.example.main.remoteMe.runUserSub(false, "main","showpage6");}
 BA.debugLineNum = 172;BA.debugLine="Sub ShowPage6";
Debug.ShouldStop(2048);
 BA.debugLineNum = 173;BA.debugLine="pnlMain.RemoveAllViews";
Debug.ShouldStop(4096);
main.mostCurrent._pnlmain.runVoidMethod ("RemoveAllViews");
 BA.debugLineNum = 174;BA.debugLine="pnlMain.LoadLayout(\"page6\")";
Debug.ShouldStop(8192);
main.mostCurrent._pnlmain.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("page6")),main.mostCurrent.activityBA);
 BA.debugLineNum = 175;BA.debugLine="lblTitle.Text = \"Meter To Centimeter\"";
Debug.ShouldStop(16384);
main.mostCurrent._lbltitle.runMethod(true,"setText",BA.ObjectToCharSequence("Meter To Centimeter"));
 BA.debugLineNum = 176;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}