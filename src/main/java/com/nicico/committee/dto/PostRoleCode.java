package com.nicico.committee.dto;

public enum PostRoleCode {


    BOARD_CHAIRMAN("CB1", "رئیس هیئت مدیره"),
    CEO("CEO", "مدیرعامل"),
    BOARD_MEMBER("CT1", "عضو هیئت مدیره"),
    BOARD_ALTERNATE("ALT", "عضو علی‌البدل"),


    FINANCIAL_MANAGER("FM", "مدیر مالی"),
    HR_MANAGER("HR", "مدیر منابع انسانی"),


    AUDITOR("AUD", "بازرس"),
    SIGNATORY("SIG", "امضادار");

    private final String code;
    private final String faTitle;

    PostRoleCode(String code, String faTitle) {
        this.code = code;
        this.faTitle = faTitle;
    }

    public String getCode() {
        return code;
    }

    public String getFaTitle() {
        return faTitle;
    }
}
