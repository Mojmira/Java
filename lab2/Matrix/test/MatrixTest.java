import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @org.junit.jupiter.api.Test
    void Matrix() {
        Matrix testedMatrix = new Matrix(2,2);
        double expectedSize=2;

        assertEquals(testedMatrix.rows, expectedSize);
        assertEquals(testedMatrix.cols, expectedSize);
    }

    @org.junit.jupiter.api.Test
    void testMatrix() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2,10},{3,4}});
        double expectedRows=2;
        double expectedCols=3;
        double expectedArray[] = new double[]{1,2,10,3,4,0};

        assertEquals(testedMatrix.rows, expectedRows);
        assertEquals(testedMatrix.cols, expectedCols);
        assertArrayEquals(testedMatrix.data, expectedArray);

    }

    @org.junit.jupiter.api.Test
    void asArray() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        double expextedArray[][] = new double[][]{{1,2},{3,4}};
        double testedArray[][];
        testedArray=testedMatrix.asArray();
        assertArrayEquals(expextedArray, testedArray);
    }

    @org.junit.jupiter.api.Test
    void get() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        double expectedValue=4;
        assertEquals(testedMatrix.get(1,1), expectedValue);
    }

    @org.junit.jupiter.api.Test
    void set() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        double expectedValue=7;
        testedMatrix.set(0,0, expectedValue);
        assertEquals(expectedValue,testedMatrix.data[0]);
    }

    @org.junit.jupiter.api.Test
    void testToString() {

        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        String expectedString="[[1.0, 2.0, ], [3.0, 4.0, ], ]";

        String resultString= testedMatrix.toString();

        assertEquals(resultString, expectedString);

    }

    @org.junit.jupiter.api.Test
    void reshape() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});

        try {
            testedMatrix.reshape(1,4);
        }
        catch (Exception e){
            System.out.println(e.toString());
            fail();
        }
    }


    @org.junit.jupiter.api.Test
    void add() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix testedMatrix2 = new Matrix(new double[][]{{-1,-2},{-3,-4}});

        Matrix finalMatrix=testedMatrix.add(testedMatrix2);
        assertEquals(finalMatrix.frobenius(), 0);

    }

    @org.junit.jupiter.api.Test
    void sub() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix testedMatrix2 = new Matrix(new double[][]{{1,2},{3,4}});

        Matrix finalMatrix=testedMatrix.sub(testedMatrix2);
        assertEquals(finalMatrix.frobenius(), 0);
    }

    @org.junit.jupiter.api.Test
    void mul() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix testedMatrix2 = new Matrix(new double[][]{{1,1},{1,1}});

        Matrix supportMatrix = testedMatrix.mul(testedMatrix2);
        assertArrayEquals(testedMatrix.data, supportMatrix.data);
    }

    @org.junit.jupiter.api.Test
    void div() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix expectedMatrix = new Matrix(new double[][]{{1,1},{1,1}});
        Matrix resultMatrix = testedMatrix.div(testedMatrix);

        assertArrayEquals(resultMatrix.data, expectedMatrix.data);

    }

    @org.junit.jupiter.api.Test
    void testAdd() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix expectedMatrix = new Matrix(new double[][]{{2,3},{4,5}});
        Matrix resultMatrix=testedMatrix.add(1);
        assertArrayEquals(resultMatrix.data, expectedMatrix.data);
    }

    @org.junit.jupiter.api.Test
    void testSub() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix expectedMatrix = new Matrix(new double[][]{{0,1},{2,3}});
        Matrix resultMatrix=testedMatrix.sub(1);
        assertArrayEquals(resultMatrix.data, expectedMatrix.data);
    }

    @org.junit.jupiter.api.Test
    void testMul() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix resultMatrix=testedMatrix.mul(1);
        assertArrayEquals(resultMatrix.data, resultMatrix.data);

    }

    @org.junit.jupiter.api.Test
    void testDiv() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix resultMatrix=testedMatrix.div(1);
        assertArrayEquals(resultMatrix.data, resultMatrix.data);
    }

    @org.junit.jupiter.api.Test
    void dot() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,2},{3,4}});
        Matrix testedMatrix2 = new Matrix(new double[][]{{5,6},{7,8}});

        Matrix expectedMatrix = new Matrix(new double[][]{{23,34},{31,46}});

        Matrix resultMatrix=testedMatrix.dot(testedMatrix2);
        assertArrayEquals(resultMatrix.data, expectedMatrix.data);
    }

    @org.junit.jupiter.api.Test
    void frobenius() {
        Matrix testedMatrix = new Matrix(new double[][]{{1,1},{1,1}});
        double expectedFrobenius=2;
        assertEquals(testedMatrix.frobenius(), expectedFrobenius);
    }

}