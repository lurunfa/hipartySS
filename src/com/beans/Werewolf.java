package com.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/16.
 */
public class Werewolf {
    /**
     * 上帝ID
     * 预言家是否存在
     * 女巫是否存在
     * 猎人是否存在
     * 白痴是否存在
     * 小偷是否存在
     * 丘比特是否存在
     * 守卫是否存在
     * 长老是否存在
     * 小女孩是否存在
     */
    private String godId;
    private boolean seerIs;
    private boolean witchIs;
    private boolean hunterIs;
    private boolean idiotIs;
    private boolean thiefIs;
    private boolean cupidIs;
    private boolean guardIs;
    private boolean presbyterIs;
    private boolean girlIs;
    private int werewolfnum;
    private int villagernum;
    private List<RoomUser> unplayerlist=new ArrayList<>();

    public String getGodId() {
        return godId;
    }

    public void setGodId(String godId) {
        this.godId = godId;
    }

    public boolean isSeerIs() {
        return seerIs;
    }

    public void setSeerIs(boolean seerIs) {
        this.seerIs = seerIs;
    }

    public boolean isWitchIs() {
        return witchIs;
    }

    public void setWitchIs(boolean witchIs) {
        this.witchIs = witchIs;
    }

    public boolean isHunterIs() {
        return hunterIs;
    }

    public void setHunterIs(boolean hunterIs) {
        this.hunterIs = hunterIs;
    }

    public boolean isIdiotIs() {
        return idiotIs;
    }

    public void setIdiotIs(boolean idiotIs) {
        this.idiotIs = idiotIs;
    }

    public boolean isThiefIs() {
        return thiefIs;
    }

    public void setThiefIs(boolean thiefIs) {
        this.thiefIs = thiefIs;
    }

    public boolean isCupidIs() {
        return cupidIs;
    }

    public void setCupidIs(boolean cupidIs) {
        this.cupidIs = cupidIs;
    }

    public boolean isGuardIs() {
        return guardIs;
    }

    public void setGuardIs(boolean guardIs) {
        this.guardIs = guardIs;
    }

    public boolean isPresbyterIs() {
        return presbyterIs;
    }

    public void setPresbyterIs(boolean presbyterIs) {
        this.presbyterIs = presbyterIs;
    }

    public boolean isGirlIs() {
        return girlIs;
    }

    public void setGirlIs(boolean girlIs) {
        this.girlIs = girlIs;
    }

    public int getWerewolfnum() {
        return werewolfnum;
    }

    public void setWerewolfnum(int werewolfnum) {
        this.werewolfnum = werewolfnum;
    }

    public int getVillagernum() {
        return villagernum;
    }

    public void setVillagernum(int villagernum) {
        this.villagernum = villagernum;
    }

    public List getunPlayerlist() {
        return unplayerlist;
    }

    public void setunPlayerlist(List<RoomUser> unplayerlist) {
        this.unplayerlist = unplayerlist;
    }
    //enum Type{
//        seer(1);
//
//    Type(int i) {
//    }
//}
}
