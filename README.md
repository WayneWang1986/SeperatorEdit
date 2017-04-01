## An EditText for number input with separators.

When you input a long sequence of digits, such as credit card number, ID number or phone number, it's often a good practice to add separators between different parts of the sequence. This customized EditText can make this easy. You can specify certain String as separator, set max input count of the digit sequence and fully control where to add a separator.


![](http://i2.muimg.com/567571/d4fe00581e1e9426.gif)

### How to use?

Copy **SeparatorEditText.java** to your project and enjoy using it.

### Customization
SeparatorEditText has several methods for your customization:
- setSeparator: set arbitrary string used as separator.
- setMax: set max input digit count for the edit, any input beyond the limit is invalid.
- getRealText: get the real input (without any separator) of this edit.
- setCondition: set a conditon to determine where to add a separator. This method receive a parameter with type **SeparatorCondition**, it's an interface with only one method **addSeparator** to tell where to add a separator. The paramter **charIndex** refers to the digit index of the real input content. For example ,when your input is "123456", **charIndex** goes between 0 to 5 as the index of the input digits. If you want to a separator after "2",  simply return true when **charIndex" is "1". See the sample to figure it out.


-----------
# License

    Copyright 2017 Wayne Wang

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
