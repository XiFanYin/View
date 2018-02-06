package viewdemo.tumour.com.a51ehealth.view.net.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import viewdemo.tumour.com.a51ehealth.view.R;


public class LoadingDialog extends Dialog {

	public LoadingDialog(Context context) {
		super(context, R.style.LoadingDialogTheme);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commom_loading_layout);

	}


}

