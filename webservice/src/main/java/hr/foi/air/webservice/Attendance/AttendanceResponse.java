package hr.foi.air.webservice.Attendance;

import java.util.List;

import hr.foi.air.webservice.Data.DataResponse;

public class AttendanceResponse extends DataResponse {
    //Todo: Klasa trenutno ne odgovara JSON responseu sa web servisa i trebalo bi je u narednim commitovima ispraviti.

    List<String> AiR;
    List<String> OS2;
    List<String> SiS;

    public List<String> getAiR() {
        return AiR;
    }

    public List<String> getOS2() {
        return OS2;
    }

    public List<String> getSiS() {
        return SiS;
    }

    public void setAiR(List<String> aiR) {
        AiR = aiR;
    }

    public void setOS2(List<String> OS2) {
        this.OS2 = OS2;
    }

    public void setSiS(List<String> siS) {
        SiS = siS;
    }
}
