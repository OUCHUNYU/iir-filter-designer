package org.jdsp.iirfilterdesigner.designers;

import static org.junit.Assert.assertEquals;

import org.jdsp.iirfilterdesigner.designers.AbstractIIRDesigner.BandstopObjectiveFunction;
import org.jdsp.iirfilterdesigner.exceptions.BadFilterParametersException;
import org.jdsp.iirfilterdesigner.model.FilterType;
import org.junit.Test;

/**
 * This class performs unit tests on {@link ChebyshevIIRDesigner} class.
 */
public class ChebyshevIIRDesignerTest {

    /**
     * an instance of {@link Chebyshev1IIRDesigner} needed to call all the test
     * methods
     */
    Chebyshev1IIRDesigner iirdesigner = new Chebyshev1IIRDesigner();

    /**
     * Test method for
     * {@link ChebyshevIIRDesigner#calculateFilterOrder(org.signalml.math.iirdesigner.FilterType, double[], double[], double, double, boolean) }.
     */
    @Test
    public void testCalculateFilterOrder() throws BadFilterParametersException {

        int filterOrder;

        // digital lowpass
        filterOrder = iirdesigner.calculateFilterOrder(FilterType.LOWPASS, 0.4, 0.7, 3, 40, false);
        assertEquals(4, filterOrder);

        // digital highpass
        filterOrder = iirdesigner.calculateFilterOrder(FilterType.HIGHPASS, 0.7, 0.3, 1, 80, false);
        assertEquals(6, filterOrder);

        // digital bandstop
        filterOrder = iirdesigner.calculateFilterOrder(FilterType.BANDSTOP, new double[]{0.5, 0.8},
                new double[]{0.6, 0.7}, 0.5, 100, false);
        assertEquals(8, filterOrder);

        // analog bandpass
        filterOrder = iirdesigner.calculateFilterOrder(FilterType.BANDPASS, new double[]{0.6, 0.7},
                new double[]{0.55, 0.8}, 0.5, 100, true);
        assertEquals(10, filterOrder);

    }

    /**
     * Test method for
     * {@link ChebyshevIIRDesigner#calculateBandstopObjectiveFunctionValue(double, int, double[], double[], double, double) }.
     */
    @Test
    public void testBandstopObjectiveFunction() {

        // chebyshev test (both chebyshev's are implemented by the same code)
        BandstopObjectiveFunction bo = iirdesigner.new BandstopObjectiveFunction(0, new double[]{0.1, 0.8},
                new double[]{0.4, 0.6}, 3, 20);
        assertEquals(3.7599806708583481, bo.value(0.0), 1e-8);

    }

}