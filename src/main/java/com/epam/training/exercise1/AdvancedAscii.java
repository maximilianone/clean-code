package com.epam.training.exercise1;

import lombok.extern.slf4j.Slf4j;

/**
 * Class to draw ascii picture.
 */
@Slf4j
public final class AdvancedAscii {
    /**
     * start min value.
     */
    private static final int MIN_VALUE = 255;
    /**
     * min value quantifier.
     */
    private static final int MIN_QUANTIFIER = 3;
    /**
     * height value.
     */
    private static final int HEIGHT = 60;
    /**
     * width value.
     */
    private static final int WIDTH = 200;
    /**
     * chars by darkness.
     */
    private static final char[] CHARS_BY_DARKNESS = {'#', '@', 'X', 'L', 'I', ':', '.', ' '};

    /**
     * max value.
     */
    private static int max;
    /**
     * min value.
     */
    private static int min = MIN_VALUE * MIN_QUANTIFIER;

    /**
     * Image.
     */
    private static Image image;

    /**
     * X step.
     */
    private static int stepX;

    /**
     * Y step.
     */
    private static int stepY;


    private AdvancedAscii() {
    }

    /**
     * Main method to draw Ascii image.
     *
     * @param args - starting values
     */
    public static void main(final String[] args) {
        image = Image.createImage("pair_hiking.png");

        stepY = image.getHeight() / HEIGHT;
        stepX = image.getWidth() / WIDTH;

        findMinMax();
        drawImage();
    }

    private static void findMinMax() {
        for (int y = 0; y < image.getHeight(); y += stepY) {
            for (int x = 0; x < image.getWidth(); x += stepX) {
                int sum = finSum(x, y);
                sum = sum / stepY / stepX;
                if (max < sum) {
                    max = sum;
                }
                if (min > sum) {
                    min = sum;
                }
            }
        }
    }

    private static void drawImage() {
        final StringBuilder builder = new StringBuilder();
        for (int y = 0; y < image.getHeight() - stepY; y += stepY) {
            builder.setLength(0);
            for (int x = 0; x < image.getWidth() - stepX; x += stepX) {
                int sum = finSum(x, y);
                sum = sum / stepY / stepX;
                builder.append(CHARS_BY_DARKNESS[(sum - min) * CHARS_BY_DARKNESS.length / (max - min + 1)]);
            }
            log.info(builder.toString());
        }
    }

    private static int finSum(final int x, final int y) {
        int sum = 0;
        for (int avgy = 0; avgy < stepY; avgy++) {
            for (int avgx = 0; avgx < stepX; avgx++) {
                sum = sum + (image.getRed(new Coordinate(x, y)) + image.getBlue(new Coordinate(x, y)) + image.getGreen(new Coordinate(x, y)));
            }
        }
        return sum;
    }

}
