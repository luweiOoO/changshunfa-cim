package com.cim.common.constant;

/**
 * 订单流程枚举类
 */
public class ProcedureConstants {

    //流程状态枚举类
    public enum ProcedureStatusEnum{

        UNKNOW("0","未知"),
        UNSTART("1","未开始"),
        ONGOING("2","进行中"),
        FINISH("3","已完成");

        private String statusCode;

        private String statusName;

        private ProcedureStatusEnum(String statusCode,String statusName){
            this.statusCode = statusCode;
            this.statusName = statusName;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public String getStatusName() {
            return statusName;
        }

        public static ProcedureStatusEnum findByStatusCode(String statusCode){
            for(ProcedureStatusEnum obj : ProcedureStatusEnum.values()){
                if(obj.getStatusCode().equals(statusCode)){
                    return obj;
                }
            }
            return UNKNOW;
        }
    }


    //返工标识枚举类
    public enum ReworkEnum{

        NO("0","未返工"),
        YES("1","已返工");

        private String statusCode;

        private String statusName;

        private ReworkEnum(String statusCode,String statusName){
            this.statusCode = statusCode;
            this.statusName = statusName;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public String getStatusName() {
            return statusName;
        }

        public static ProcedureStatusEnum findByStatusCode(String statusCode){
            for(ProcedureStatusEnum obj : ProcedureStatusEnum.values()){
                if(obj.getStatusCode().equals(statusCode)){
                    return obj;
                }
            }
            return null;
        }
    }
}
