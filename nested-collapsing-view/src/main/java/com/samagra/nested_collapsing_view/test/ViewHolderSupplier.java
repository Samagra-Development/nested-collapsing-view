package com.samagra.nested_collapsing_view.test;

import android.view.View;

public interface ViewHolderSupplier<VH> {
    VH get(View view);
}
