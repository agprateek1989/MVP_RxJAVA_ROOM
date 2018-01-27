package org.demo.demoarch;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import org.demo.demoarch.core.cache.RepoDetail;

/**
 * Created by pagga9 on 1/26/2018.
 */

public class ChoiceDialog extends android.support.v4.app.DialogFragment {

    private RepoDetail detail;
    public static ChoiceDialog newInstance(RepoDetail detail){
        ChoiceDialog dialog = new ChoiceDialog();
        dialog.detail = detail;
        return dialog;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return  new AlertDialog.Builder(getActivity())
                .setMessage(" Do you want to go the repository or owner's repository")
                .setPositiveButton("Owner",
                        (DialogInterface dialogInterface, int i) -> launchBrowser(detail.getOwnerUrl()))
                .setNegativeButton("Repo",
                        (DialogInterface dialogInterface, int i) -> launchBrowser(detail.getRepoUrl())).show();
    }

    private void launchBrowser(String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
