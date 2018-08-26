package com.lh.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor7;

/**
 * @author lh
 * @datetime 2018/8/26 23:47
 */
public final class AppRegisterVisitor extends SimpleAnnotationValueVisitor7{

    private Filer mFiler = null;
    private TypeMirror mTypeMirror = null;
    private String mPackageName = null;

    public void setFiler(Filer mFiler) {
        this.mFiler = mFiler;
    }

    @Override
    public Object visitString(String s, Object p) {
        mPackageName = s;
        return p;
    }

    @Override
    public Object visitType(TypeMirror typeMirror, Object p) {
        mTypeMirror = typeMirror;
        return p;
    }

    private void generatorJavaCode(){
        final TypeSpec targetActivity = TypeSpec
                .classBuilder("AppRegister")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mTypeMirror))
                .build();

        final JavaFile javaFile = JavaFile.builder(mPackageName+".wxapi",targetActivity)
                .addFileComment("微信广播接收器")
                .build();
        try {
            javaFile.writeTo(mFiler);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
