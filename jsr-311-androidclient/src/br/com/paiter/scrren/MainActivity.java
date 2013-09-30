package br.com.paiter.scrren;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import br.com.paiter.core.rest.RestClient;
import br.com.paiter.jsr_311_androidclient.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void acessandoListaRest(String addressServer){
		final RestClient restClient = new RestClient(addressServer);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		// builder.setTitle(R.string.hello_world).setNeutralButton
	}
}
