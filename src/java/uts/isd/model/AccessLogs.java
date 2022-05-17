package uts.isd.model;

public class AccessLogs {
    private int accessLogsID;
    private String date;
    private String activity;

    public AccessLogs(int accessLogsID, String date, String activity) {
        this(date, activity);
        this.accessLogsID = accessLogsID;
    }

    public AccessLogs(String date, String activity) {
        this.date = date;
        this.activity = activity;
    }

    public int getAccessLogsID() {
        return accessLogsID;
    }

    public String getDate() {
        return date;
    }

    public String getActivity() {
        return activity;
    }
}
