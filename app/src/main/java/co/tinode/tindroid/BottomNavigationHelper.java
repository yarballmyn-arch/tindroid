package co.tinode.tindroid;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Helper class to manage bottom navigation interactions
 */
public class BottomNavigationHelper implements BottomNavigationView.OnItemSelectedListener {
    
    private final ChatsActivity activity;
    private final BottomNavigationView bottomNav;

    public BottomNavigationHelper(ChatsActivity activity, BottomNavigationView bottomNav) {
        this.activity = activity;
        this.bottomNav = bottomNav;
        this.bottomNav.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        if (itemId == R.id.nav_channels) {
            showChannelsFragment();
            return true;
        } else if (itemId == R.id.nav_chats) {
            showChatsFragment();
            return true;
        }
        
        return false;
    }

    private void showChannelsFragment() {
        Fragment fragment = activity.getSupportFragmentManager()
                .findFragmentByTag("channels");
        
        if (fragment == null) {
            fragment = new ChatsFragment();
            Bundle args = new Bundle();
            args.putBoolean("show_channels", true);
            fragment.setArguments(args);
        }
        
        FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.contentFragment, fragment, "channels")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }

    private void showChatsFragment() {
        Fragment fragment = activity.getSupportFragmentManager()
                .findFragmentByTag("chats");
        
        if (fragment == null) {
            fragment = new ChatsFragment();
            Bundle args = new Bundle();
            args.putBoolean("show_channels", false);
            fragment.setArguments(args);
        }
        
        FragmentTransaction transaction = activity.getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.contentFragment, fragment, "chats")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();
    }
}
