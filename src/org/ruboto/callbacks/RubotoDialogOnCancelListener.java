package org.ruboto.callbacks;

import org.jruby.Ruby;
import org.jruby.javasupport.util.RuntimeHelpers;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.javasupport.JavaUtil;
import org.jruby.exceptions.RaiseException;
import org.ruboto.Script;

public class RubotoDialogOnCancelListener implements android.content.DialogInterface.OnCancelListener {
  private Ruby __ruby__;

  public static final int CB_CANCEL = 0;
  private IRubyObject[] callbackProcs = new IRubyObject[1];



  private Ruby getRuby() {
    if (__ruby__ == null) __ruby__ = Script.getRuby();
    return __ruby__;
  }

  public void setCallbackProc(int id, IRubyObject obj) {
    callbackProcs[id] = obj;
  }
	
  public void onCancel(android.content.DialogInterface dialog) {
    if (callbackProcs[CB_CANCEL] != null) {
      try {
        RuntimeHelpers.invoke(getRuby().getCurrentContext(), callbackProcs[CB_CANCEL], "call" , JavaUtil.convertJavaToRuby(getRuby(), dialog));
      } catch (RaiseException re) {
        re.printStackTrace();
      }
    }
  }
}
