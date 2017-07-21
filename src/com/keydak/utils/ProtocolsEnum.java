package com.keydak.utils;

/**
 * Created by caisz on 2017/6/26.
 */
public enum ProtocolsEnum {
    airc800 {
        public String label() {
            return "airc800";
        }
        public String code() {
            return "airc800";
        }
    }
    ,CD500_EMH {
        public String label() {
            return "CD500环境主机";
        }
        public String code() {
            return "CD500-主机EMH";
        }
    }
    ,EMH_CA_20131205 {
        public String label() {
            return "CD2000P环境主机";
        }
        public String code() {
            return "EMH-CA_2013-12-05";
        }
    }
    ,JiaLiTu_KN10_AC {
        public String label() {
            return "佳力图空调KN10";
        }
        public String code() {
            return "JiaLiTu_KN10_AC";
        }
    }
    ,JYDP100_AC {
        public String label() {
            return "JYDP100-AC";
        }
        public String code() {
            return "JYDP100-AC";
        }
    }
    ,JYDP100_DC {
        public String label() {
            return "JYDP100-DC";
        }
        public String code() {
            return "JYDP100-DC";
        }
    }
    ,K400_SCA {
        public String label() {
            return "K400_SCA空调";
        }
        public String code() {
            return "K400_SCA";
        }
    }
    ,KeShiDa_FocusAir_AC {
        public String label() {
            return "科士达空调FocusAir";
        }
        public String code() {
            return "KeShiDa_FocusAir_AC";
        }
    }
    ,keydak_pdu {
        public String label() {
            return "金盾PDU";
        }
        public String code() {
            return "keydak_pdu";
        }
    }
    ,ma01c {
        public String label() {
            return "交流配电柜ma01c";
        }
        public String code() {
            return "ma01c";
        }
    }
    ,ma01d {
        public String label() {
            return "直流配电柜ma01d";
        }
        public String code() {
            return "ma01d";
        }
    }
    ,sems2 {
        public String label() {
            return "sems2温湿度探头";
        }
        public String code() {
            return "sems2";
        }
    }
    ,SI_PDU3 {
        public String label() {
            return "SI-PDU3";
        }
        public String code() {
            return "SI-PDU3";
        }
    }
    ,ups_link_communications_apc {
        public String label() {
            return "ups-link-communications-apc";
        }
        public String code() {
            return "ups-link-communications-apc";
        }
    }
    ,ups_eletronic_main {
        public String label() {
            return "ups-电总";
        }
        public String code() {
            return "ups-电总";
        }
    }
    ,ups_MR33 {
        public String label() {
            return "ups_MR33";
        }
        public String code() {
            return "ups_MR33";
        }
    }
    ,XRow_DX_A1 {
        public String label() {
            return "XRow-DX-A1空调";
        }
        public String code() {
            return "XRow-DX-A1";
        }
    }
    ,XRow_DX_A4 {
        public String label() {
            return "XRow-DX-A4空调";
        }
        public String code() {
            return "XRow-DX-A4";
        }
    }
    ,YinWeiTeng_36KW_AC {
        public String label() {
            return "英威腾空调36KW";
        }
        public String code() {
            return "YinWeiTeng_36KW_AC";
        }
    },keydak_u_bit {
        public String label() {
            return "KeydakU位检测";
        }
        public String code() {
            return "keydak_u_bit";
        }
    },keydak_asset {
        public String label() {
            return "Keydak资产管理";
        }
        public String code() {
            return "keydak_asset";
        }
    },TH_ADA {
        public String label() {
            return "温控灯带TH_ADA";
        }
        public String code() {
            return "TH_ADA";
        }
    },keydak_SCCU_EN_V1 {
        public String label() {
            return "增强采集单元SCCU_EN_V1.0";
        }
        public String code() {
            return "keydak_SCCU_EN_V1";
        }
    };

    public String label(){
        return this.label();
    }
    public String code() {
        return this.code();
    }
}
