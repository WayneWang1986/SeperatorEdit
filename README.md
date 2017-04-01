## An EditText for number input with separators.

When you input a long sequence of digits, such as credit card number, ID number or phone number, it's often a good practice to add separators between different parts of the sequence. This customized EditText can make this easy. You can specify certain String as separator, set max input count of the digit sequence and fully control where to add a separator.


![Alt text](./sample.gif)

### How to use?

Copy **SeparatorEditText.java** to your project and enjoy using it.

### Customization
SeparatorEditText has several methods for your customization:
- setSeparator: set arbitrary string used as separator.
- setMax: set max input digit count for the edit, any input beyond the limit is invalid.
- getRealText: get the real input (without any separator) of this edit.
- setCondition: set a conditon to determine where to add a separator. This method receive a parameter with type **SeparatorCondition**, it's an interface with only one method **addSeparator** to tell where to add a separator. The paramter **charIndex** refers to the digit index of the real input content. For example ,when your input is "123456", **charIndex** goes between 0 to 5 as the index of the input digits. If you want to a separator after "2",  simply retrun true when **charIndex" is "1". See the sample to figure it out.
