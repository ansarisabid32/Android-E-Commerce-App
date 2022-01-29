package com.birgunjbazar.mybazar;

public class RewardsModel {
    private String rewardTitle,rewardValidity,rewardBody;

    public RewardsModel(String rewardTitle, String rewardValidity, String rewardBody) {
        this.rewardTitle = rewardTitle;
        this.rewardValidity = rewardValidity;
        this.rewardBody = rewardBody;
    }

    public String getRewardTitle() {
        return rewardTitle;
    }

    public void setRewardTitle(String rewardTitle) {
        this.rewardTitle = rewardTitle;
    }

    public String getRewardValidity() {
        return rewardValidity;
    }

    public void setRewardValidity(String rewardValidity) {
        this.rewardValidity = rewardValidity;
    }

    public String getRewardBody() {
        return rewardBody;
    }

    public void setRewardBody(String rewardBody) {
        this.rewardBody = rewardBody;
    }
}
