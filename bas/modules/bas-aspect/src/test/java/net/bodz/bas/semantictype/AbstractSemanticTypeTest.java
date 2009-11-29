package net.bodz.bas.semantictype;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.Random;

import net.bodz.bas.api.exceptions.CreateException;
import net.bodz.bas.api.exceptions.ParseException;
import net.bodz.bas.aspect.typeinfo.TestUtils;
import net.bodz.bas.aspect.typeinfo.ValidateException;
import net.bodz.bas.semantictype.annotation.TypeParameter;

import org.junit.Test;

public class AbstractSemanticTypeTest {

    static class Money
            extends AbstractSemanticType<BigDecimal> {

        @TypeParameter
        private final int precise = 3;

        @TypeParameter
        private final Format format;

        public Money() {
            super("Money", null, "###, ###.##", //
                    BigDecimal.class, null);
            StringBuffer pattern = new StringBuffer();
            pattern.append(",###.");
            for (int i = 0; i < precise; i++)
                pattern.append("#");
            format = new DecimalFormat(pattern.toString());
        }

        @Override
        public Object parse(String text)
                throws ParseException {
            try {
                return format.parseObject(text);
            } catch (java.text.ParseException e) {
                throw new ParseException(e.getMessage(), e);
            }
        }

        @Override
        public String format(Object object) {
            return format.format(object);
        }

        @Override
        public void validate(Object object, Object validateContext)
                throws ValidateException {
        }

        @Override
        public BigDecimal newSampleInstance()
                throws CreateException {
            double random = new Random().nextDouble() * 1000000000.0;
            BigDecimal sample = new BigDecimal(random);
            return sample;
        }

    }

    ISemanticType<BigDecimal> moneyType = new Money();

    @Test
    public void testNewSample()
            throws Exception {
        for (int i = 0; i < 10; i++) {
            BigDecimal sample = moneyType.newSampleInstance();
            TestUtils.testConvertLoss(moneyType, sample);
        }
    }

    @Test
    public void testParseFormat()
            throws Exception {
        BigDecimal sample = new BigDecimal("1234567.890123");
        String sampleText = moneyType.getFormatter().format(sample, null);
        assertEquals("1,234,567.89", sampleText);
        TestUtils.testConvertLoss(moneyType, sample);
    }

    @Test
    public void testDump()
            throws Exception {
        System.out.println(SemanticTypeUtil.dumpTypeStructure(moneyType));
    }

}
