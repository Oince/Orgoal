"use strict";(self["webpackChunkfront"]=self["webpackChunkfront"]||[]).push([[270],{3270:function(e,n,t){t.r(n),t.d(n,{default:function(){return g}});var s=t(6252),r=t(9963),o=t(3577);const i=e=>((0,s.dD)("data-v-974bc6b0"),e=e(),(0,s.Cn)(),e),a={class:"signin"},l=i((()=>(0,s._)("h1",null,"Orgoal",-1))),u={class:"signinform"},c=i((()=>(0,s._)("label",{for:"emailInput"},"ID",-1))),m=i((()=>(0,s._)("label",{for:"memberPasswordInput"},"PW",-1))),d={class:"buttons"};function p(e,n,t,i,p,f){return(0,s.wg)(),(0,s.iD)("div",a,[l,(0,s._)("form",u,[(0,s._)("p",null,[c,(0,s.wy)((0,s._)("input",{type:"text",id:"emailInput",class:"input_text",ref:"emailInput","onUpdate:modelValue":n[0]||(n[0]=n=>e.email=n),placeholder:"아이디"},null,512),[[r.nr,e.email,void 0,{trim:!0}]])]),(0,s._)("p",null,[m,(0,s.wy)((0,s._)("input",{type:"password",id:"memberPasswordInput",class:"input_text",ref:"memberPasswordInput","onUpdate:modelValue":n[1]||(n[1]=n=>e.memberPassword=n),placeholder:"비밀번호"},null,512),[[r.nr,e.memberPassword,void 0,{trim:!0}]])]),(0,s._)("p",d,[(0,s._)("button",{onClick:n[2]||(n[2]=(0,r.iM)((n=>e.doSignin()),["prevent"])),class:"button blue"},"로그인"),(0,s._)("button",{onClick:n[3]||(n[3]=(0,r.iM)((n=>e.doCancel()),["prevent"])),class:"button"},"취소")])]),(0,s._)("p",null,(0,o.zw)(e.errorMessage),1)])}var f=t(3907),h={name:"SignIn",setup:function(){const e=(0,f.oR)();let n="",t="",s="";const r=function(){if(""==this.email)return alert("아이디를 입력하세요."),void this.$refs.emailInput.focus();if(""==this.memberPassword)return alert("비밀번호를 입력하세요."),void this.$refs.memberPasswordInput.focus();let n={email:this.email,password:this.memberPassword};e.dispatch("signin/doSignin",n).then((()=>{console.log(e.state.signin.token),e.dispatch("nickname/reqNickname",this.email).catch((e=>console.log(e))),this.$router.push("/")})).catch((e=>{this.errorMessage=e.response.data.errormessage}))},o=function(){this.$router.push("../")};return{email:n,memberPassword:t,errorMessage:s,doSignin:r,doCancel:o}},mounted(){this.$refs.emailInput.focus()}},b=t(3744);const w=(0,b.Z)(h,[["render",p],["__scopeId","data-v-974bc6b0"]]);var g=w}}]);
//# sourceMappingURL=270.7b2aaef4.js.map