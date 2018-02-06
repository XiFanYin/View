package viewdemo.tumour.com.a51ehealth.view.bean;

/**
 * Created by Administrator on 2018/2/6.
 */

public class LoginBean {


    /**
     * data : {"token":"013d27c543c743b9aeb52d6ffec465f4_9967faac0be64fdf90efca5830983ccd_201712281751337772","user":{"xingb":"2","sjhm":"15517108399","zhaop":"http://47.93.136.56:7011//upload/images_from_app/doctor/20171228163552_5522.jpg","ssks":"内分泌","name":"何望","instroduction":"主治内分泌失调引起的疾病","zz":"糖尿病","signature":"013d27c543c743b9aeb52d6ffec465f4","certificate_code":"155353535353533","certificate_photo":"013d27c543c743b9aeb52d6ffec465f4","org_id":"7ea78bc197f44ac299a3522c53f10805","org_name":"枫杨社区服务中心"}}
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
         * token : 013d27c543c743b9aeb52d6ffec465f4_9967faac0be64fdf90efca5830983ccd_201712281751337772
         * user : {"xingb":"2","sjhm":"15517108399","zhaop":"http://47.93.136.56:7011//upload/images_from_app/doctor/20171228163552_5522.jpg","ssks":"内分泌","name":"何望","instroduction":"主治内分泌失调引起的疾病","zz":"糖尿病","signature":"013d27c543c743b9aeb52d6ffec465f4","certificate_code":"155353535353533","certificate_photo":"013d27c543c743b9aeb52d6ffec465f4","org_id":"7ea78bc197f44ac299a3522c53f10805","org_name":"枫杨社区服务中心"}
         */

        private String token;
        private UserBean user;

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * xingb : 2
             * sjhm : 15517108399
             * zhaop : http://47.93.136.56:7011//upload/images_from_app/doctor/20171228163552_5522.jpg
             * ssks : 内分泌
             * name : 何望
             * instroduction : 主治内分泌失调引起的疾病
             * zz : 糖尿病
             * signature : 013d27c543c743b9aeb52d6ffec465f4
             * certificate_code : 155353535353533
             * certificate_photo : 013d27c543c743b9aeb52d6ffec465f4
             * org_id : 7ea78bc197f44ac299a3522c53f10805
             * org_name : 枫杨社区服务中心
             */

            private String xingb;
            private String sjhm;
            private String zhaop;
            private String ssks;
            private String name;
            private String instroduction;
            private String zz;
            private String signature;
            private String certificate_code;
            private String certificate_photo;
            private String org_id;
            private String org_name;

            public String getXingb() {
                return xingb;
            }

            public void setXingb(String xingb) {
                this.xingb = xingb;
            }

            public String getSjhm() {
                return sjhm;
            }

            public void setSjhm(String sjhm) {
                this.sjhm = sjhm;
            }

            public String getZhaop() {
                return zhaop;
            }

            public void setZhaop(String zhaop) {
                this.zhaop = zhaop;
            }

            public String getSsks() {
                return ssks;
            }

            public void setSsks(String ssks) {
                this.ssks = ssks;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getInstroduction() {
                return instroduction;
            }

            public void setInstroduction(String instroduction) {
                this.instroduction = instroduction;
            }

            public String getZz() {
                return zz;
            }

            public void setZz(String zz) {
                this.zz = zz;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getCertificate_code() {
                return certificate_code;
            }

            public void setCertificate_code(String certificate_code) {
                this.certificate_code = certificate_code;
            }

            public String getCertificate_photo() {
                return certificate_photo;
            }

            public void setCertificate_photo(String certificate_photo) {
                this.certificate_photo = certificate_photo;
            }

            public String getOrg_id() {
                return org_id;
            }

            public void setOrg_id(String org_id) {
                this.org_id = org_id;
            }

            public String getOrg_name() {
                return org_name;
            }

            public void setOrg_name(String org_name) {
                this.org_name = org_name;
            }
        }
    }


}
