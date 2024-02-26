package com.demo.global.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MathUtil {

    /**
     * 더블형 리스트의 평균값을 구하는 메서드
     *
     * @param marks
     * @return
     */
    public static double calculateAverage(List<Double> marks) {
        double sum = 0;
        if (!marks.isEmpty()) {
            for (double mark : marks) {
                sum += mark;
            }
            return sum / marks.size();
        }
        return sum;
    }

    /**
     * @param value1 값1
     * @param value2 값2
     * @return 평균값
     */
    public static double calculateAverage(double value1, double value2) {
        return (value1 + value2) / 2;
    }

    /**
     * 더블형 리스트의 모든 값을 더하기
     *
     * @param arr
     * @return
     */
    public static double total(double[] arr) {
        double total = 0;
        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        return total;
    }

    /**
     * 배열의 평균값을 계산
     *
     * @param arr
     * @return
     */
    public static double mean(double[] arr) {
        double total = 0;
        for (int i = 0; i < arr.length; i++) {
            total = total + arr[i];
        }
        return total / arr.length;
    }

    /**
     * 배열의 평균값을 계산한 후 값 소수점 올림
     *
     * @param arr
     * @return
     */
    public static double meanUp(double[] arr) {
        double total = 0;
        for (int i = 0; i < arr.length; i++) {
            total = total + arr[i];
        }
        return Math.ceil(total / arr.length);
    }

    /**
     * 배열의 평균값을 계산한 후 값 소수점 내림
     *
     * @param arr
     * @return
     */
    public static double meanDown(double[] arr) {
        double total = 0;
        for (int i = 0; i < arr.length; i++) {
            total = total + arr[i];
        }
        return Math.floor(total / arr.length);
    }

    /**
     * 배열의 최대값을 찾는 메서드
     *
     * @param arr 더블형 배열
     * @return 배열의 최대값
     */
    public static double max(double[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return arr[maxIndex];
    }

    /**
     * 배열의 최대값 인덱스를 찾는 메서드
     *
     * @param arr 더블형 배열
     * @return 배열의 최대값 인덱스
     */
    public static int maxIndex(double[] arr) {
        int maxIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * 배열의 최대값 인덱스를 찾는 메서드
     *
     * @param arr 더블형 배열
     * @return 배열의 최대값 인덱스
     */
    public static int[] maxIndex(double[][] arr) {
        int maxIndexX = 0;
        int maxIndexY = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] > arr[maxIndexX][maxIndexY]) {
                    maxIndexX = i;
                    maxIndexY = j;
                }
            }
        }
        return new int[]{maxIndexX, maxIndexY};
    }

    /**
     * 배열의 최소값을 찾는 메서드
     *
     * @param arr 더블형 배열
     * @return 배열의 최소값
     */
    public static double min(double[] arr) {
        int minIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }
        return arr[minIndex];
    }

    /**
     * 주어진 배열의 중간값을 찾는 메서드 단, 해당 배열의 요소가 짝수개일 경우에는 구한 두개의 중간값중 낮은 값을 리턴함.
     *
     * @param arr 더블형 배열
     * @return 배열의 중간값
     */
    public static double median(double[] arr) {
        double[] arrClone = new double[arr.length];
        System.arraycopy(arr, 0, arrClone, 0, arr.length);
        Arrays.sort(arrClone);
        return arrClone[(int) (Math.round(arrClone.length / 2.0) - 1)];
    }

    /**
     * 배열의 편차값을 구하는 메서드
     *
     * @param arr 더블형 배열
     * @return 배열의 편차값
     */
    public static double variance(double[] arr) {
        return variance(arr, mean(arr));
    }

    /**
     * 배열의 편차값을 구하는 메서드
     *
     * @param arr  더블형 배열
     * @param mean 더블형 배열의 평균값
     * @return 배열의 편차값
     */
    public static double variance(double[] arr, double mean) {
        double totalDev = 0;
        for (int i = 0; i < arr.length; i++) {
            totalDev = totalDev + (mean - arr[i]) * (mean - arr[i]);
        } // Sample estimate of variance so divide by n-1.
        return totalDev / (arr.length - 1);
    }

    /**
     * 배열의 표준편차값을 구하는 메서드
     *
     * @param variance 편차
     * @return 표준편차
     */
    public static double stdDev(double variance) {
        return Math.sqrt(variance);
    }

    /**
     * 배열의 표준편차값을 구하는 메서드
     *
     * @param arr 더블형 배열
     * @return 표준편차
     */
    public static double stdDev(double[] arr) {
        return stdDev(variance(arr));
    }

    /**
     * 배열간의 RMS차를 구하는 메서드
     *
     * @param arr1 더블형 배열
     * @param arr2 더블형 배열
     * @return RMS차
     * @throws IllegalArgumentException * 배열간의 길이가 다를 경우
     */
    public static double RMS(double[] arr1, double[] arr2) {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("getRMSDifference: aar1 and aar2 " + "must be the same length.");
        }
        double totalSqDiff = 0;
        for (int i = 0; i < arr1.length; i++) {
            double diff = (arr1[i] - arr2[i]);
            totalSqDiff += diff * diff;
        }
        return Math.sqrt(totalSqDiff / arr1.length);
    }

    /**
     * 현재 진행 퍼센트 표시
     *
     * @param count
     * @param totalCount
     * @return
     */
    public static double progressTotalPercent(int count, int totalCount) {
        if (count == 0 || totalCount == 0) {
            return 0;
        }
        return (count * 100.0f) / totalCount;
    }

    /**
     * 원하는 자릿수에서 반올림 (0.6612, 2) => 0.66
     *
     * @param value
     * @param n
     * @return
     */
    public static double getDecimalRounding(double value, int n) {
        if (value == 0 || n == 0) {
            return 0;
        }
        double squared = Math.pow(10.0, n);
        return Math.round(value * squared) / squared;
    }

    /**
     * searchByte의 값 중에 inputByte와 일치하는 count의 index 출력
     *
     * @param searchByte
     * @param inputByte
     * @param count
     * @return
     */
    public static int getSearchByteInputIndex(byte searchByte[], byte inputByte, int count) {
        int startCount = 0;
        for (int i = 0; i < searchByte.length + 1; i++) {
            boolean found = false;
            if (searchByte[i] == inputByte) {
                found = true;
            }
            if (found && ++startCount == count) {
                return i;
            }
        }
        return -1;
    }

    /**
     * searchByte의 값 중에 inputByte와 일치하는 count의 index 출력
     *
     * @param searchByte
     * @param inputByte
     * @param returnCount
     * @return
     */
    public static int getSearchByteArrayInputIndex(byte searchByte[], byte inputByte[], int returnCount) {
        int startCount = 0;
        for (int i = 0; i < (searchByte.length - inputByte.length) + 1; i++) {
            boolean found = true;
            for (int j = 0; j < inputByte.length; j++) {
                if (searchByte[i + j] == inputByte[j]) {
                    continue;
                }
                found = false;
                break;
            }
            if (found && ++startCount == returnCount) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param n1 하한값
     * @param n2 상한값
     * @return
     */
    public static int randomRange(int n1, int n2) {
        return (int) (Math.random() * (n2 - n1 + 1)) + n1;
    }


    /**
     * @param n1 하한값
     * @param n2 상한값
     * @return
     */
    public static double randomFloatRange(double n1, double n2) {
        return (double) (Math.random() * (n2 - n1 + 1)) + n1;
    }

    public static final int CONNECTED_CAR_BATTERY = 800;

    /**
     * 선박에 연결된 차량 배터리 잔여 용량 퍼센트
     *
     * @param currentVoltage 현재 전력량
     * @param decimalPlaces  소수점 자리수 (0: 0, 1:0.0, 2:0.00 ...)
     * @return 퍼센트값
     */
    public static double getChargingPanelBatteryPercent(double currentVoltage, int decimalPlaces) {
        if (currentVoltage == 0) {
            return 0;
        }
        double decimalPoint = Math.pow(10.0, decimalPlaces);
        double result = (currentVoltage / CONNECTED_CAR_BATTERY) * 100;
        return Math.round(result * decimalPoint) / decimalPoint;
    }

    public static int toIntExact(long num) {
        return Math.toIntExact(num);
    }

}
