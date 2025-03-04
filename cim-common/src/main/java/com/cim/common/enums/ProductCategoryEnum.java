package com.cim.common.enums;

/**
 * 产品类型枚举类
 */
public class ProductCategoryEnum {

    /**
     * 产品类别
     */
    public enum ProductTypeEnum{
        WOMAN_NINE_WIG("001","女士九分头套"),
        WOMAN_TEN_WIG("002","女士全头套"),
        WOMAN_TOPPER("003","女士发片"),
        MAN_TEN_WIG("004","男士全头套"),
        MAN_TOPPER("005","男士发片");

        //产品类别编码
        private final String code;
        //产品类别名称
        private final String name;

        private ProductTypeEnum(String code,String name){
            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return this.code;
        }

        public String getName(){
            return this.name;
        }
    }

    /**
     * 网底材质
     */
    public enum ProductNetTextureEnum{

        ONE("001","弹力网"),
        TWO("002","蜂窝网"),
        THREE("003","亲肤网"),
        FOUR("004","冰丝网");

        //网底材质编码
        private final String code;
        //网底材质名称
        private final String name;

        private ProductNetTextureEnum(String code,String name){
            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return this.code;
        }

        public String getName(){
            return this.name;
        }

    }

    /**
     * 网底类别
     */
    public enum ProductNetCategoryEnum{
        ONE("001","递针旋"),
        TWO("002","递针顶"),
        THREE("003","递针分界"),
        FOUR("004","全递针"),
        FIVE("004","全蕾丝");

        //网底类别编码
        private final String code;
        //网底类别名称
        private final String name;

        private ProductNetCategoryEnum(String code,String name){
            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return this.code;
        }

        public String getName(){
            return this.name;
        }
    }

    /**
     * 头发材质
     */
    public enum ProductHairTextureEnum{
        ONE("001","泡发"),
        TWO("002","倒顺"),
        THREE("003","顺发"),
        FOUR("004","保鳞发");

        //头发材质编码
        private final String code;
        //头发材质名称
        private final String name;

        private ProductHairTextureEnum(String code,String name){
            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return this.code;
        }

        public String getName(){
            return this.name;
        }
    }

    /**
     * 订单状态
     */
    public enum OrderStatusEnum{
        BOOK("BOOK","已预定"),
        FINISH("FINISH","已完成"),
        CANCEL("CANCEL","已退货");

        //订单状态编码
        private final String code;
        //订单状态名称
        private final String name;

        private OrderStatusEnum(String code,String name){
            this.code = code;
            this.name = name;
        }

        public String getCode(){
            return this.code;
        }

        public String getName(){
            return this.name;
        }
    }


}
