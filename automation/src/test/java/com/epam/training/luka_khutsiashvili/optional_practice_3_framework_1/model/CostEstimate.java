package com.epam.training.luka_khutsiashvili.optional_practice_3_framework_1.model;

public class CostEstimate {
    private final String numOfInstance;
    private final String operatingSystem;
    private final String provisioningModel;
    private final String machineType;
    private final String gpuModel;
    private final String gpuCount;
    private final String localSSD;
    private final String region;

    public CostEstimate(String numOfInstance, String operatingSystem, String provisioningModel,
                        String machineType, String gpuModel, String gpuCount,
                        String localSSD, String region) {
        this.numOfInstance = numOfInstance;
        this.operatingSystem = operatingSystem;
        this.provisioningModel = provisioningModel;
        this.machineType = machineType;
        this.gpuModel = gpuModel;
        this.gpuCount = gpuCount;
        this.localSSD = localSSD;
        this.region = region;
    }

    // Getters for each field
    public String getNumOfInstance() { return numOfInstance; }
    public String getOperatingSystem() { return operatingSystem; }
    public String getProvisioningModel() { return provisioningModel; }
    public String getMachineType() { return machineType; }
    public String getGpuModel() { return gpuModel; }
    public String getGpuCount() { return gpuCount; }
    public String getLocalSSD() { return localSSD; }
    public String getRegion() { return region; }
}
