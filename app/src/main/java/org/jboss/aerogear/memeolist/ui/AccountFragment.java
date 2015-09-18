package org.jboss.aerogear.memeolist.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.jboss.aerogear.android.pipe.PipeManager;
import org.jboss.aerogear.android.pipe.callback.AbstractFragmentCallback;
import org.jboss.aerogear.memeolist.R;
import org.jboss.aerogear.memeolist.content.vo.RedHatUser;
import org.jboss.aerogear.memeolist.utils.KeycloakEnabledPicasso;

import java.util.List;

/**
 * Created by summers on 6/22/15.
 */
public class AccountFragment extends Fragment {

    private View view;
    private ImageView avatar;
    private TextView displayName;
    private TextView emailAddress;
    private TextView bio;
    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.account_fragment, null);
        avatar = (ImageView) view.findViewById(R.id.avatar);
        displayName = (TextView) view.findViewById(R.id.screen_name);
        emailAddress = (TextView) view.findViewById(R.id.email_address);
        bio = (TextView) view.findViewById(R.id.bio_text);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadAccount();
    }

    private void loadAccount() {
        PipeManager.getPipe("kc-user", this, getActivity()).read(new LoadAccountCallback());
    }

    private static class LoadAccountCallback extends AbstractFragmentCallback<List<RedHatUser>> {
        @Override
        public void onSuccess(List<RedHatUser> redHatUser) {
            AccountFragment accountFragment = (AccountFragment) getFragment();
            KeycloakEnabledPicasso.getPicasso(accountFragment.getActivity()).load(redHatUser.get(0).getPhotoUrl()).fit().into(accountFragment.avatar);
            accountFragment.displayName.setText(redHatUser.get(0).getDisplayName());
            accountFragment.emailAddress.setText(redHatUser.get(0).getEmailAddress());
            accountFragment.bio.setText(redHatUser.get(0).getBio());
        }

        @Override
        public void onFailure(Exception e) {

        }
    }
}
