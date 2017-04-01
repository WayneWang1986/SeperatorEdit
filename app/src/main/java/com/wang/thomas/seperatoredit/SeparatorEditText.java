/*
 * Copyright [2017] [https://github.com/WayneWang1986]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.wang.thomas.seperatoredit;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

/**
 * Created by WayneWang on 2017/3/24.
 */

public class SeparatorEditText extends AppCompatEditText {
    public static final String WHITE_SPACE = " ";
    private String separator = WHITE_SPACE;
    private int max = Integer.MAX_VALUE;
    private StringBuilder builder = new StringBuilder();
    private SeparatorCondition condition;

    public SeparatorEditText(Context context) {
        super(context);
        init();
    }

    public SeparatorEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SeparatorEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // only support number input now.
        setInputType(InputType.TYPE_CLASS_NUMBER);
        addTextChangedListener(new TextWatcher() {
            private int separatorCount;
            private int cursorPos;
            private boolean preventLoop;
            private String beforeText;
            private int start;
            private int before;
            private int count;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (preventLoop) {
                    return;
                }

                beforeText = s.toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (preventLoop) {
                    return;
                }

                this.start = start;
                this.before = before;
                this.count = count;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (preventLoop) {
                    preventLoop = false;
                    setSelection(cursorPos + separatorCount);
                    return;
                }

                int delCount = getRealText(s).length() - max;
                if (delCount > 0) {
                    s.delete(start + count - delCount, start + count);
                }

                String rawText = s.toString();
                String textBeforeStart = s.subSequence(0, start).toString();
                int realStart = getRealText(textBeforeStart).length();
                cursorPos = Math.min(max, realStart + count);

                boolean delSingleChar = (before == 1 && count == 0);
                boolean delSeparator = delSingleChar && (beforeText.charAt(start) + "").equals(separator);

                if (delSeparator) {
                    cursorPos--;
                    builder.delete(0, builder.length());
                    builder.append(rawText.substring(0, start - 1))
                            .append(rawText.substring(start));
                    rawText = builder.toString();
                }

                separatorCount = 0;
                builder.delete(0, builder.length());
                String realText = getRealText(rawText);
                int length = Math.min(max, realText.length());

                for (int i = 0; i < length; i++) {
                    builder.append(realText.charAt(i));
                    if (condition.addSeparator(i) && i < length - 1) {
                        builder.append(separator);
                        if (i < cursorPos) {
                            separatorCount++;
                        }
                    }
                }

                preventLoop = true;
                setText(builder.toString());
            }
        });
    }

    /**
     * get the real text(without separator) of this edit.
     */
    public String getRealText(CharSequence rawText) {
        return rawText.toString().replace(separator, "");
    }

    /**
     * set custom separator
     */
    public void setSeparator(String seperator) {
        this.separator = seperator;
    }

    /**
     * set the separator condition
     */
    public void setCondition(SeparatorCondition condition) {
        this.condition = condition;
    }

    /**
     * set max number of valid digits
     */
    public void setMax(int max) {
        this.max = max;
    }

    interface SeparatorCondition {
        boolean addSeparator(int charIndex);
    }
}
