package hu.ait.android.shoppinglist.touch;

/**
 * Created by zhaozhaoxia on 11/4/17.
 */

public interface ItemTouchHelperAdapter {

    void onItemDismiss(int position);

    void onItemMove(int fromPosition, int toPosition);
}
