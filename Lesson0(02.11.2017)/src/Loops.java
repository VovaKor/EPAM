
/**
 *
 * @author Vova Korobko
 */
public class Loops {

public static void main(String[] args) {
int shapeSize=10;   
    /*
   Isosceles triangle
                          * 
                        *   * 
                      *       * 
                    *           * 
                  *               * 
                *                   * 
              *                       * 
            *                           * 
          *                               * 
        *   *   *   *   *   *   *   *   *   * 
*/

                   for (int j=0; j<(shapeSize-1); j++)  System.out.print(" ");
    System.out.print("* ");
    System.out.print("\n");
                   for (int j=1; j<shapeSize-1; j++)
                        {
                            for (int k=0;k<(shapeSize-1-j); k++) System.out.print(" ");
                            System.out.print("* ");
                            for (int k=0;k<(j*2)-2; k++) System.out.print(" ");        
                            System.out.print("* ");
                            System.out.print("\n");
                        }
    for (int j=0; j<shapeSize; j++) System.out.print("* ");

System.out.print("\n");
System.out.print("\n");
/*
 Right triangle
                    * 
                    * * 
                    *   * 
                    *     * 
                    *       * 
                    *         * 
                    *           * 
                    *             * 
                    *               * 
                    * * * * * * * * * * 
*/
           
    System.out.print("* ");
    System.out.print("\n");
           for (int j=1; j<(shapeSize-1); j++)
                        {
                            System.out.print("* ");
                            for (int k=0;k<(j*2)-2; k++) System.out.print(" ");        
                            System.out.print("* ");
                            System.out.print("\n");
                        }
          for (int j=0; j<shapeSize; j++) System.out.print("* ");
    System.out.print("\n");
    System.out.print("\n");
/*
Rectangle
          * * * * * * * * * * * * * * * * * * * * 
          *                                     * 
          *                                     * 
          *                                     * 
          *                                     * 
          *                                     * 
          *                                     * 
          *                                     * 
          *                                     * 
          * * * * * * * * * * * * * * * * * * * * 
*/
                 
        for (int j=0; j<shapeSize*2; j++) System.out.print("* ");
System.out.print("\n");
        for (int j=1; j<(shapeSize-1); j++)
                        {                           
                            System.out.print("* ");
                            for (int k=0;k<(shapeSize*4-4); k++) System.out.print(" ");        
                            System.out.print("* ");
                            System.out.print("\n");
                        }
        for (int j=0; j<shapeSize*2; j++) System.out.print("* ");
System.out.print("\n");
System.out.print("\n");
/*
Rhombus
                       * 
                      * * 
                     *   * 
                    *     * 
                   *       * 
                  *         * 
                 *           * 
                *             * 
               *               * 
              *                 * 
               *               * 
                *             * 
                 *           * 
                  *         * 
                   *       * 
                    *     * 
                     *   * 
                      * * 
                       * 
*/

                   for (int j=0; j<(shapeSize-1); j++)  System.out.print(" ");
    System.out.print("* ");
    System.out.print("\n");
                   for (int j=1; j<shapeSize-1; j++)
                        {
                            for (int k=0;k<(shapeSize-1-j); k++) System.out.print(" ");
                            System.out.print("* ");
                            for (int k=0;k<(j*2)-2; k++) System.out.print(" ");        
                            System.out.print("* ");
                            System.out.print("\n");
                        }
                   for (int j=shapeSize-1; j>0; j--)
                        {
                            for (int k=0;k<(shapeSize-1-j); k++) System.out.print(" ");
                            System.out.print("* ");
                            for (int k=0;k<(j*2)-2; k++) System.out.print(" ");        
                            System.out.print("* ");
                            System.out.print("\n");
                        }
    for (int j=0; j<(shapeSize-1); j++)  System.out.print(" ");
    System.out.print("* ");

}
}
