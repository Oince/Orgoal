"use strict";(self["webpackChunkfront"]=self["webpackChunkfront"]||[]).push([[853],{5853:function(t,a,e){e.r(a),e.d(a,{default:function(){return E}});var i=e(6252),n=e(3577);const s=t=>((0,i.dD)("data-v-d1a641c2"),t=t(),(0,i.Cn)(),t),c={class:"mypage"},o=s((()=>(0,i._)("div",{class:"mypage-head"},"My Page",-1))),y={class:"mypage-body"},r={class:"nickname"},l=(0,i.Uk)("님, 반갑습니다! "),d=s((()=>(0,i._)("hr",null,null,-1)));function v(t,a,e,s,v,u){const g=(0,i.up)("myactivity");return(0,i.wg)(),(0,i.iD)(i.HY,null,[(0,i._)("div",c,[o,(0,i._)("div",y,[(0,i._)("span",r,(0,n.zw)(t.nickname),1),l])]),d,(0,i.Wm)(g)],64)}var u=e(3907);const g=t=>((0,i.dD)("data-v-2d555f49"),t=t(),(0,i.Cn)(),t),m={class:"myactivity"},p=g((()=>(0,i._)("div",{class:"myactivity-head"},"My Activity",-1))),k={class:"myactivitiy-table-container"},_={class:"myactivity-table"},h=g((()=>(0,i._)("td",{class:"myactivity-notice"},[(0,i._)("svg",{version:"1.0",xmlns:"http://www.w3.org/2000/svg",width:"6pt",height:"6pt",viewBox:"0 0 48.000000 48.000000",preserveAspectRatio:"xMidYMid meet"},[(0,i._)("g",{transform:"translate(0.000000,48.000000) scale(0.100000,-0.100000)",stroke:"none",class:(0,n.C_)(["red-dot"])},[(0,i._)("path",{d:"M160 467 c-49 -16 -133 -102 -148 -153 -28 -94 -8 -169 63 -239 102\r\n-103 243 -101 338 5 90 100 90 220 0 320 -65 72 -158 97 -253 67z"})])])],-1))),w={class:"myactivity-status"},f={key:0},b={key:1},D={key:2},M={class:"myactivity-title"};function A(t,a,e,s,c,o){const y=(0,i.up)("router-link");return(0,i.wg)(),(0,i.iD)("div",m,[p,(0,i._)("div",k,[(0,i._)("table",_,[(0,i._)("tbody",null,[((0,i.wg)(!0),(0,i.iD)(i.HY,null,(0,i.Ko)(t.myActivities,(a=>((0,i.wg)(),(0,i.iD)("tr",{class:"table-body",key:a},[h,(0,i._)("td",w,["r"==a.status?((0,i.wg)(),(0,i.iD)("span",f,"모집중")):(0,i.kq)("",!0),"p"==a.status?((0,i.wg)(),(0,i.iD)("span",b,"진행중")):(0,i.kq)("",!0),"e"==a.status?((0,i.wg)(),(0,i.iD)("span",D,"완료")):(0,i.kq)("",!0)]),(0,i._)("td",M,[(0,i.Wm)(y,{to:t.getURLbyActivityID(a.aid)},{default:(0,i.w5)((()=>[(0,i.Uk)((0,n.zw)(a.title),1)])),_:2},1032,["to"])])])))),128))])])])])}var C=e(9669),R=e.n(C),U={name:"MyActivity",setup:function(){const t=(0,u.oR)(),a=t.state.signin.token;let e=[],i={headers:{token:this.token}};function n(t){return"/activity/"+t.toString()}return R().get("/mypage/activity",i).then((t=>{console.log("GET Mypage Activity SUCCESS"),t.data.forEach((t=>{e.push(t)})).catch((t=>{console.log(t)}))})),t.dispatch("myactivity/getMyactivity",e),{token:a,myActivities:e,getURLbyActivityID:n}}},I=e(3744);const S=(0,I.Z)(U,[["render",A],["__scopeId","data-v-2d555f49"]]);var q=S,x={name:"MyPage",components:{myactivity:q},setup:function(){const t=(0,u.oR)(),a=t.state.signin.token,e=(0,i.Fl)((()=>t.getters["nickname/getNickname"]));return{token:a,nickname:e}}};const z=(0,I.Z)(x,[["render",v],["__scopeId","data-v-d1a641c2"]]);var E=z}}]);
//# sourceMappingURL=853.64765770.js.map