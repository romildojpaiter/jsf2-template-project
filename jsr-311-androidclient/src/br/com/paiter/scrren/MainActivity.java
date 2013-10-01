package br.com.paiter.scrren;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import br.com.paiter.core.rest.RestClient;
import br.com.paiter.entity.Aluno;
import br.com.paiter.jsr_311_androidclient.R;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// click-handlers for buttons
		View addButton = findViewById(R.id.add_button);
		addButton.setOnClickListener(this);

		View listButton = findViewById(R.id.list_button);
		listButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		TextView textServer = (TextView) findViewById(R.id.textServer);
		final String serverAddress = textServer.getText().toString();

		switch (v.getId()) {
		case R.id.list_button:
			try {
				final RestClient restClient = new RestClient(serverAddress);
				AlertDialog.Builder builder = new AlertDialog.Builder(this);
				builder.setTitle(R.string.students);
				builder.setNeutralButton(R.string.cancel, null);
				final List<Aluno> alunos = restClient.getList("", Aluno.class);
				String[] alunosNames = new String[alunos.size()];
				for (int i = 0; i < alunos.size(); i++) {
					Aluno aluno = alunos.get(i);
					alunosNames[i] = aluno.getNome();
				}
				builder.setSingleChoiceItems(alunosNames, -1, null);
				builder.setPositiveButton(R.string.btEdit,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								int selectedPosition = ((AlertDialog) dialog)
										.getListView().getCheckedItemPosition();
								Aluno aluno = alunos.get(selectedPosition);
								editAluno(restClient, aluno);
							}
						});
				builder.setNegativeButton(R.string.btErase,
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								int selectedPosition = ((AlertDialog) dialog)
										.getListView().getCheckedItemPosition();
								Aluno aluno = alunos.get(selectedPosition);
								String result = restClient.deleteObject(""
										+ aluno.getId());
								Toast.makeText(getApplicationContext(),
										"result:" + result, Toast.LENGTH_LONG)
										.show();
							}
						});
				AlertDialog alert = builder.create();
				alert.show();
			} catch (Exception e) {
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				Toast.makeText(getApplicationContext(), "error:" + errors,
						Toast.LENGTH_LONG).show();
			}
			break;
		case R.id.add_button:
			try {
				final RestClient restClient = new RestClient(serverAddress);
				Aluno aluno = new Aluno();
				editAluno(restClient, aluno);
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "error:" + e,
						Toast.LENGTH_LONG).show();
			}
			break;
		}
	}

	private void editAluno(final RestClient restClient, final Aluno aluno) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle(R.string.student);
		builder.setNegativeButton(R.string.cancel, null);
		
		final View layout = getLayoutInflater().inflate(R.layout.aluno_form,
				null);
		builder.setView(layout);
		
		final EditText editNome = (EditText) layout.findViewById(R.id.editNome);
		final EditText editNota = (EditText) layout.findViewById(R.id.editNota);
		
		editNome.setText(aluno.getNome() != null ? aluno.getNome() : "");
		editNota.setText(aluno.getNota() + "");
		DialogInterface.OnClickListener onclick = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int selected) {
				final String nome = editNome.getText().toString();
				final String nota = editNota.getText().toString();
				aluno.setNome(nome);
				aluno.setNota(Integer.parseInt(nota));
				restClient.postObject(aluno, "");
			}
		};
		builder.setPositiveButton(R.string.btSave, onclick);
		AlertDialog alert = builder.create();
		alert.show();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Handle the back button
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showQuitConfirmDialog(this);
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}

	public static void showQuitConfirmDialog(final Activity context) {
		// Ask the user if they want to quit
		new AlertDialog.Builder(context)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle(R.string.quit)
				.setMessage(R.string.really_quit)
				.setPositiveButton(R.string.yes,
						new DialogInterface.OnClickListener() {

							// @Override
							public void onClick(DialogInterface dialog,
									int which) {

								// Stop the activity
								context.finish();
							}

						}).setNegativeButton(R.string.no, null).show();
	}

}
