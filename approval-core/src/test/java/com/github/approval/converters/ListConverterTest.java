package com.github.approval.converters;

/*
 * #%L
 * approval
 * %%
 * Copyright (C) 2014 Nikolavp
 * %%
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
 * #L%
 */

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * User: github
 * Date: 28/02/14
 * Time: 17:47
 */
public class ListConverterTest {
    @Test
    public void shouldBeComposableWithAnotherConverter() throws Exception {
        //assign
        Converter<String> other = Mockito.mock(Converter.class);
        Mockito.when(other.getRawForm(Mockito.anyString())).thenReturn("test".getBytes(StandardCharsets.UTF_8));
        ListConverter<String> listConverter = new ListConverter<>(other);
        List<String> strings = Arrays.asList("test", "foo", "bar");

        //act
        byte[] rawForm = listConverter.getRawForm(strings);

        //assert
        Assert.assertThat(new String(rawForm, StandardCharsets.UTF_8), CoreMatchers.equalTo("[0] = test\n[1] = test\n[2] = test\n\n"));
        Mockito.verify(other, Mockito.times(3)).getRawForm(Mockito.anyString());
    }
}
