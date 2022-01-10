package com.fjmg.inventory.base;

public interface OnRepositoryCallback {
    void onSuccess(String message);
    void onFailure(String message);
}
