package org.art.samples.core.reflection.transaction_analyzer;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Transaction {

    String startTransactionMessage() default "Transaction is started...";
    String endTransactionMessage() default "Transaction is finished!";
}
