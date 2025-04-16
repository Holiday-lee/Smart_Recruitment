/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package distsys.smart_recruitment.auth;

import com.google.rpc.RequestInfo;
import io.grpc.Attributes;
import io.grpc.CallCredentials;
import io.grpc.CallCredentials.MetadataApplier;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.util.concurrent.Executor;

/**
 *
 * @author jiaki
 */
public class BearerToken implements CallCredentials {

    private String value;

    public BearerToken(String value) {
        this.value = value;
    }


    @Override
    public void applyRequestMetadata(MethodDescriptor<?, ?> method, Attributes attributes, Executor executor, MetadataApplier applier) {
        System.out.println("In the correct applyRequestMetadata implementation");
        executor.execute(() -> {
            try {
                Metadata headers = new Metadata();
                headers.put(Constants.AUTHORIZATION_METADATA_KEY, String.format("%s %s", Constants.BEARER_TYPE, value));
                applier.apply(headers);
            } catch (Throwable e) {
                applier.fail(Status.UNAUTHENTICATED.withCause(e));
            }
        });
    }


    @Override
    public void thisUsesUnstableApi() {
        // noop
    }

}