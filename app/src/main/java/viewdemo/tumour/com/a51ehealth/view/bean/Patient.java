package viewdemo.tumour.com.a51ehealth.view.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public class Patient {


    /**
     * data : {"infoCount":42,"patientList":[{"patientId":"16d6cb568c2449ebb4dfb75a639713e2","patient_name":"殷飞龙","patient_sex":"1","patient_age":25,"patient_type":"普通群众","user_id":"aac6a3cc744c4f3cb73ff328e388cdfd","patient_birth_year_month_day":19920308000000,"zhaopUrl":"http://47.93.136.56:7011//static/images/defaultPatient.png"},{"patientId":"a88dd0b09f3a49eab8da3883c0996d2d","patient_name":"thgyfy","patient_sex":"1","patient_age":118,"patient_type":"普通群众","user_id":null,"patient_birth_year_month_day":19000101}]}
     * error_code : 0
     * msg : Success
     */

    private DataBean data;
    private int error_code;
    private String msg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class DataBean {
        /**
         * infoCount : 42
         * patientList : [{"patientId":"16d6cb568c2449ebb4dfb75a639713e2","patient_name":"殷飞龙","patient_sex":"1","patient_age":25,"patient_type":"普通群众","user_id":"aac6a3cc744c4f3cb73ff328e388cdfd","patient_birth_year_month_day":19920308000000,"zhaopUrl":"http://47.93.136.56:7011//static/images/defaultPatient.png"},{"patientId":"a88dd0b09f3a49eab8da3883c0996d2d","patient_name":"thgyfy","patient_sex":"1","patient_age":118,"patient_type":"普通群众","user_id":null,"patient_birth_year_month_day":19000101}]
         */

        private int infoCount;
        private List<PatientListBean> patientList;

        public int getInfoCount() {
            return infoCount;
        }

        public void setInfoCount(int infoCount) {
            this.infoCount = infoCount;
        }

        public List<PatientListBean> getPatientList() {
            return patientList;
        }

        public void setPatientList(List<PatientListBean> patientList) {
            this.patientList = patientList;
        }

        public static class PatientListBean {
            /**
             * patientId : 16d6cb568c2449ebb4dfb75a639713e2
             * patient_name : 殷飞龙
             * patient_sex : 1
             * patient_age : 25
             * patient_type : 普通群众
             * user_id : aac6a3cc744c4f3cb73ff328e388cdfd
             * patient_birth_year_month_day : 19920308000000
             * zhaopUrl : http://47.93.136.56:7011//static/images/defaultPatient.png
             */

            private String patientId;
            private String patient_name;
            private String patient_sex;
            private int patient_age;
            private String patient_type;
            private String user_id;
            private long patient_birth_year_month_day;
            private String zhaopUrl;

            public String getPatientId() {
                return patientId;
            }

            public void setPatientId(String patientId) {
                this.patientId = patientId;
            }

            public String getPatient_name() {
                return patient_name;
            }

            public void setPatient_name(String patient_name) {
                this.patient_name = patient_name;
            }

            public String getPatient_sex() {
                return patient_sex;
            }

            public void setPatient_sex(String patient_sex) {
                this.patient_sex = patient_sex;
            }

            public int getPatient_age() {
                return patient_age;
            }

            public void setPatient_age(int patient_age) {
                this.patient_age = patient_age;
            }

            public String getPatient_type() {
                return patient_type;
            }

            public void setPatient_type(String patient_type) {
                this.patient_type = patient_type;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public long getPatient_birth_year_month_day() {
                return patient_birth_year_month_day;
            }

            public void setPatient_birth_year_month_day(long patient_birth_year_month_day) {
                this.patient_birth_year_month_day = patient_birth_year_month_day;
            }

            public String getZhaopUrl() {
                return zhaopUrl;
            }

            public void setZhaopUrl(String zhaopUrl) {
                this.zhaopUrl = zhaopUrl;
            }
        }
    }
}
