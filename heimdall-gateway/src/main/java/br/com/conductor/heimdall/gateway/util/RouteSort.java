package br.com.conductor.heimdall.gateway.util;

/*-
 * =========================LICENSE_START==================================
 * heimdall-gateway
 * ========================================================================
 * Copyright (C) 2018 Conductor Tecnologia SA
 * ========================================================================
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
 * ==========================LICENSE_END===================================
 */


import java.util.Comparator;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

/**
 * Represents the algorithm to sort {@link ZuulRoute} correctly.
 * 
 * @author Marcos Filho
 * @author Marcelo Rodrigues
 *
 */
public class RouteSort implements Comparator<ZuulRoute> {

	@Override
    public int compare(ZuulRoute r1, ZuulRoute r2) {
         String pattern1 = r1.getPath();
         String pattern2 = r2.getPath();
         
         if (pattern1.startsWith("/*")) return 1;
         if (pattern2.startsWith("/*")) return -1;
         
         String[] split = pattern1.split("/");
         String[] split2 = pattern2.split("/");
         
         for (String firstSplit : split) {
              for (String secondSplit : split2) {
                   if (firstSplit.equals("**") && secondSplit.equals("**")) return 0;
                   if (firstSplit.equals("**") && !secondSplit.equals("**")) return -1;
                   if (!firstSplit.equals("**") && secondSplit.equals("**")) return 1;
                   return pattern1.compareTo(pattern2);
              }
              
         }

         return pattern1.compareTo(pattern2);
    }


}
