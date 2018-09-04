package service;

import play.mvc.Http;

public class UserAgent {
    private boolean isMobileDevice;

    public UserAgent(Http.Request request){
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.toLowerCase().contains("mobi")){
            isMobileDevice = true;
        }
    }
    public boolean isMobileDevice() {
        return isMobileDevice;
    }

    public void setMobileDevice(boolean mobileDevice) {
        isMobileDevice = mobileDevice;
    }


}
