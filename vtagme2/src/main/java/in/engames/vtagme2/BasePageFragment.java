package in.engames.vtagme2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by nmannem on 30/10/13.
 */
public class BasePageFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    public static final String ARG_SECTION_ID = "section_id";

    protected Bundle args;
    public BasePageFragment(int sectionNumber) {
        if (args == null) args = new Bundle();
        args.putInt(ARG_SECTION_ID, sectionNumber);
        setArguments(args);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainPageActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_ID));
    }
}