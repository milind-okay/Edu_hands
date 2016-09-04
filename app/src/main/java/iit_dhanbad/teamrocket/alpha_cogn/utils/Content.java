package iit_dhanbad.teamrocket.alpha_cogn.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iit_dhanbad.teamrocket.alpha_cogn.R;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Content {

    /**
     * An array of sample (dummy) items.
     */


    public static final List<ContentItem> ITEMS = new ArrayList<ContentItem>();
    public static final List<ContentItem> ITEMS_MH = new ArrayList<ContentItem>();
    public static final List<ContentItem> ITEMS_M = new ArrayList<ContentItem>();
    public static final List<ContentItem> ITEMS_MT = new ArrayList<ContentItem>();
    public static final List<ContentItem> ITEMS_MP = new ArrayList<ContentItem>();
    public static final List<ContentItem> ITEMS_EXP = new ArrayList<ContentItem>();
    public static String mClinicItem1[];

    public  static  String[] web = {
            "Pethood",
            "Photography",
            "Motherhood",
            "Science",
            "Fine Art",
            "Fitness",
            "Music",
            "Film Making",
            "Computer Science",
            "Nature",
            "Social Science",
            "International Languages",
            "Mathematics"

    } ;
    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, ContentItem> ITEM_MAP = new HashMap<String, ContentItem>();

    private static final int COUNT = 4;

    static {
        // Add some sample items.
        int micon[] = new int[5];
        String mContentItem[] = new String[4];
        String mBasicProfile[] = new String[5];
        String mMedicalHistory[] = new String[5];
        String mMedication[] = new String[5];
        String mMediclTreatments[] = new String[2];
         mClinicItem1 = new String[4];
        String mExperience[] = new String[2];
        micon[0] = R.drawable.availability;
        micon[1] = R.drawable.add_user;
        micon[2] = R.drawable.location;
        micon[3] = R.drawable.budget;

        mContentItem[0] = "Availability";
        mContentItem[1] = "# Visits";
        mContentItem[2] = "Clinic Type";
        mContentItem[3] = "Expense";

        mExperience[0] = "Single Visit";
        mExperience[1] = "Any";

        mBasicProfile[0] = "Name";
        mBasicProfile[1] = "Age";
        mBasicProfile[2]  ="Mobile";
        mBasicProfile[3] = "Email";
        mBasicProfile[4] = "Blood group";


        mMedicalHistory[0] = "Diabetes";
        mMedicalHistory[1] = "High Blood Pressure";
        mMedicalHistory[2] = "Low Blood Pressure";
        mMedicalHistory[3] = "High Thyroid";
        mMedicalHistory[4] = "Low Thyroid";

        mMedication[0] = "Aspirin - daily dosage";
        mMedication[1] = "Heparin - daily dosage";
        mMedication[2] = "Steroids - daily dosage";
        mMedication[3] = "Blood Pressure regulation medications";
        mMedication[4] = "Antidepressants";

        mMediclTreatments[0] = "Cardiac Bypass surgery";
        mMediclTreatments[1] = "Linear(Hepatic) surgery";



        for (int i = 0; i <COUNT; i++) {
            addItem(createDummyItem(i,micon,mContentItem));
        }
        for(int i=0;i<mBasicProfile.length;i++){
            ITEMS_MP.add(new ContentItem(i, mBasicProfile[i], micon[0]));
        }
        for(int i=0;i<mMedicalHistory.length;i++){
            ITEMS_MH.add(new ContentItem(i, mMedicalHistory[i], micon[0]));
        }
        for(int i=0;i<mMedication.length;i++){
            ITEMS_M.add(new ContentItem(i, mMedication[i], micon[0]));
        }
        for(int i=0;i<mMediclTreatments.length;i++){
            ITEMS_MT.add(new ContentItem(i, mMediclTreatments[i], micon[0]));
        }
        for(int i=0;i<mExperience.length;i++){
            ITEMS_EXP.add(new ContentItem(i,mExperience[i],micon[0]));
        }
    }

    private static void addItem(ContentItem item) {
        ITEMS.add(item);

        ITEM_MAP.put(String.valueOf(item.id), item);
    }

    private static ContentItem createDummyItem(int position,int micon[],String mContent[]) {
        return new ContentItem(position, mContent[position], micon[position]);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class ContentItem {
        public final int id;
        public final String content;
        public final int ContentIcon;

        public ContentItem(int id, String content, int icon) {
            this.id = id;
            this.content = content;
            this.ContentIcon = icon;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
