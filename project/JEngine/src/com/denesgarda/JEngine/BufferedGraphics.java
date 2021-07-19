package com.denesgarda.JEngine;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * A record holding Graphics and a BufferedStrategy
 */
public record BufferedGraphics(Graphics graphics, BufferStrategy bufferStrategy) {
}
